package com.myseoultravel.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myseoultravel.GeoPopActivity;
import com.myseoultravel.R;
import com.myseoultravel.SelectAreaActivity;


import java.util.ArrayList;


public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {
    private ArrayList<ScheduleItem> mList;
    static final int REQUEST_CODE_ST = 1;
    static final int REQUEST_CODE_DST = 2;

    public class ScheduleViewHolder extends RecyclerView.ViewHolder {
        private TextView scheduleDayIdx;
        private TextView scheduleDate;
        private Button scheduleAddPoi;
        private Button scheduleStBtn;
        private Button scheduleDstBtn;

        private TextView scheduleSt;
        private TextView scheduleDst;

        public ScheduleViewHolder(View view) {
            super(view);
            this.scheduleDayIdx = (TextView) view.findViewById(R.id.schedule_day_idx);
            this.scheduleDate = (TextView) view.findViewById(R.id.schedule_day_date);
            this.scheduleAddPoi = (Button) view.findViewById(R.id.schedule_item_add);
            this.scheduleStBtn = (Button) view.findViewById(R.id.schedule_start_search);
            this.scheduleDstBtn = (Button) view.findViewById(R.id.schedule_end_search);
            this.scheduleSt = (TextView) view.findViewById(R.id.schedule_start_add);
            this.scheduleDst = (TextView) view.findViewById(R.id.schedule_end_add);
            scheduleSt.setSelected(true);
            scheduleDst.setSelected(true);
        }
    }


    public ScheduleAdapter(ArrayList<ScheduleItem> list) {
        this.mList = list;
    }



    @Override
    public ScheduleViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_schedule_item, viewGroup, false);

        ScheduleViewHolder viewHolder = new ScheduleViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder viewholder, int position) {

//        viewholder.scheduleDayIdx.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
//        viewholder.scheduleDate.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

        viewholder.scheduleDayIdx.setGravity(Gravity.CENTER);
        viewholder.scheduleDate.setGravity(Gravity.CENTER);

        viewholder.scheduleDayIdx.setText(mList.get(position).getScheduleDayIdx());
        viewholder.scheduleDate.setText(mList.get(position).getScheduleDate());

        viewholder.scheduleSt.setText(mList.get(position).getScheduleSt());
        viewholder.scheduleDst.setText(mList.get(position).getScheduleDst());
        viewholder.scheduleSt.setSelected(true);
        viewholder.scheduleDst.setSelected(true);

        viewholder.scheduleAddPoi.setTag(viewholder.getAdapterPosition());
        viewholder.scheduleAddPoi.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //int pos = (int) v.getTag(); 삭제할 때 사용 가능
                Intent intent = new Intent(v.getContext(), SelectAreaActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        viewholder.scheduleStBtn.setTag(viewholder.getAdapterPosition());
        viewholder.scheduleStBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                Intent intent = new Intent(v.getContext(), GeoPopActivity.class);
                intent.putExtra("pos", pos);
                ((Activity) v.getContext()).startActivityForResult(intent,REQUEST_CODE_ST);
            }
        });
        viewholder.scheduleDstBtn.setTag(viewholder.getAdapterPosition());
        viewholder.scheduleDstBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                Intent intent = new Intent(v.getContext(), GeoPopActivity.class);
                intent.putExtra("pos", pos);
                ((Activity) v.getContext()).startActivityForResult(intent,REQUEST_CODE_DST);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}
