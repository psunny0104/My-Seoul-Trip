package com.myseoultravel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.myseoultravel.R;

import java.util.ArrayList;

public class MakingAdapter extends RecyclerView.Adapter<MakingAdapter.CourseViewHolder> {
    private ArrayList<PoiItem> mList;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public class CourseViewHolder extends RecyclerView.ViewHolder{
        private TextView poiName;
        private TextView subwayEngName;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            this.poiName = (TextView) itemView.findViewById(R.id.making_poi_name);
            this.subwayEngName = (TextView) itemView.findViewById(R.id.making_subway_eng_name);
        }
    }

    public MakingAdapter(ArrayList<PoiItem> list){this.mList = list;}

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_making_item, parent, false);

        CourseViewHolder viewHolder = new CourseViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        holder.poiName.setText(mList.get(position).getPoiTitle());
        holder.subwayEngName.setText(mList.get(position).getLineName());

        holder.poiName.setTag(holder.getAdapterPosition());

        holder.subwayEngName.setTag(holder.getAdapterPosition());


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
