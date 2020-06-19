package com.myseoultavel.adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;
import com.myseoultavel.HomeActivity;
import com.myseoultrip.R;
import com.myseoultavel.ScheduleActivity;

import java.util.ArrayList;

public class TravelAdapter extends RecyclerView.Adapter<TravelAdapter.TravelViewHolder>{
    private ArrayList<TravelItem> mList;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public class TravelViewHolder extends RecyclerView.ViewHolder {
        private TextView travelIdx;
        private TextView travelStartDate;
        private TextView travelEndDate;
        private ImageButton travelDel;
        private ImageButton travelSchedule;

        public TravelViewHolder(View view) {
            super(view);
            this.travelIdx = (TextView) view.findViewById(R.id.home_idx);
            this.travelStartDate = (TextView) view.findViewById(R.id.home_start_date);
            this.travelEndDate = (TextView) view.findViewById(R.id.home_end_date);
            this.travelDel = (ImageButton) view.findViewById(R.id.home_item_delete);
            this.travelSchedule = (ImageButton) view.findViewById(R.id.home_item_schedule);
        }
    }


    public TravelAdapter(ArrayList<TravelItem> list) {
        this.mList = list;
    }



    @Override
    public TravelViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_travel_item, viewGroup, false);

        TravelViewHolder viewHolder = new TravelViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull TravelViewHolder viewholder, int position) {

        viewholder.travelIdx.setText("Travel "+mList.get(position).getTravelIdx());
        viewholder.travelStartDate.setText(mList.get(position).getTravelStartDate());
        viewholder.travelEndDate.setText(mList.get(position).getTravelEndDate());
        viewholder.travelDel.setTag(viewholder.getAdapterPosition());
        viewholder.travelDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Alert");
                builder.setMessage("Are you sure you want to erase?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("myTag", "Home: Document was delete");
                                db.collection("travel").document(mList.get(pos).getTravelId())
                                        .delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                db.collection("course").whereEqualTo("travelItemId",mList.get(pos).getTravelId())
                                                        .get()
                                                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                            @Override
                                                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                                WriteBatch batch = db.batch();
                                                                for(int i = 0 ; i<queryDocumentSnapshots.size(); i++){
                                                                    DocumentReference documentReference = queryDocumentSnapshots.getDocuments().get(i).getReference();
                                                                    batch.delete(documentReference);
                                                                }
                                                                batch.commit();
                                                            }
                                                        });
                                                db.collection("poi").whereEqualTo("travelItemId",mList.get(pos).getTravelId())
                                                        .get()
                                                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                            @Override
                                                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                                WriteBatch batch = db.batch();
                                                                for(int i = 0 ; i<queryDocumentSnapshots.size(); i++){
                                                                    DocumentReference documentReference = queryDocumentSnapshots.getDocuments().get(i).getReference();
                                                                    batch.delete(documentReference);
                                                                }
                                                                batch.commit();
                                                            }
                                                        });


                                                Intent intent = new Intent(v.getContext(), HomeActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                v.getContext().startActivity(intent);
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

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

        viewholder.travelSchedule.setTag(viewholder.getAdapterPosition());
        viewholder.travelSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();

                TravelItem travelItem = mList.get(pos);
                //Toast.makeText(getApplicationContext(), travelItem.getTravelIdx()+' '+travelItem.getTravelStartDate()+' '+travelItem.getTravelEndDate(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(v.getContext(), ScheduleActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);

                intent.putExtra("travelId",travelItem.getTravelId());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

    public void dialogShow()
    {

    }
}
