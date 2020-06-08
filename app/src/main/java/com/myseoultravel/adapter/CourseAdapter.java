package com.myseoultravel.adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.myseoultravel.CourseActivity;
import com.myseoultravel.HomeActivity;
import com.myseoultravel.R;
import com.myseoultravel.SearchDetailActivity;

import java.util.HashMap;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private HashMap<String, PoiItem> mList;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public class CourseViewHolder extends RecyclerView.ViewHolder{
        private TextView poiTitle;
        private TextView poiContentType;
        private TextView poiAddress;
        private ImageView poiImage;
        private ImageButton poiDel;
        private ImageButton poiInfo;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            this.poiTitle = (TextView) itemView.findViewById(R.id.course_item_title);
            this.poiAddress = (TextView) itemView.findViewById(R.id.course_item_address);
            this.poiImage = (ImageView) itemView.findViewById(R.id.course_item_image);
            this.poiDel = (ImageButton) itemView.findViewById(R.id.course_item_delete);
            this.poiInfo = (ImageButton) itemView.findViewById(R.id.course_item_info);
        }
    }

    public CourseAdapter(HashMap<String, PoiItem> list){this.mList = list;}

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_course_item, parent, false);

        CourseViewHolder viewHolder = new CourseViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        holder.poiTitle.setText(mList.get(String.valueOf(position)).getPoiTitle());
        holder.poiAddress.setText(mList.get(String.valueOf(position)).getPoiAddress());
        getImageByGlide(mList.get(String.valueOf(position)).getPoiImage(),holder.poiImage);

        holder.poiInfo.setTag(holder.getAdapterPosition());
        holder.poiInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SearchDetailActivity.class);
                intent.putExtra("contentId", mList.get(String.valueOf(position)).getPoiContentId());
                intent.putExtra("contentTypeId", mList.get(String.valueOf(position)).getPoiContentTypeId());
                intent.putExtra("api","tour");
                v.getContext().startActivity(intent);
            }
        });

        holder.poiDel.setTag(holder.getAdapterPosition());
        holder.poiDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
//                "scheduleItems."+pos+".scheduleDst",add,

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Alert");
                builder.setMessage("Are you sure you want to erase?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String tmp = mList.get(String.valueOf(pos)).getCourseItemId();
                                db.collection("course").document(mList.get(String.valueOf(pos)).getCourseItemId())
                                        .update("poiItemHashMap."+pos, FieldValue.delete())
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d("myTag", "Course: Document was delete");
                                                Intent intent = new Intent(v.getContext(), CourseActivity.class);
                                                intent.putExtra("courseId",mList.get(String.valueOf(pos)).getCourseItemId());
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                v.getContext().startActivity(intent);
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.e("myTag", "Course: Error delete document", e);
                                            }
                                        });


                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }


    public void getImageByGlide(String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
//                .placeholder(R.drawable.frame_item)
//                .error(R.drawable.new_error)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide.with(imageView.getContext())
                .load(url)
                .apply(options)
                .into(imageView);
    }
}
