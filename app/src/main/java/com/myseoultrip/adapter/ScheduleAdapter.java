package com.myseoultrip.adapter;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.myseoultrip.CourseActivity;
import com.myseoultrip.GeoPopActivity;
import com.myseoultrip.R;
import com.myseoultrip.adapter.ScheduleItem;


import java.util.HashMap;


public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {
    private HashMap<String, ScheduleItem> mList;
    static final int REQUEST_CODE_ST = 1;
    static final int REQUEST_CODE_DST = 2;

    public class ScheduleViewHolder extends RecyclerView.ViewHolder {
        private TextView scheduleDayIdx;
        private TextView scheduleDate;
        private Button scheduleManageCrsBtn;
        private Button scheduleTravelBtn;
        private Button scheduleStBtn;
        private Button scheduleDstBtn;

        private TextView scheduleSt;
        private TextView scheduleDst;

        public ScheduleViewHolder(View view) {
            super(view);
            this.scheduleDayIdx = (TextView) view.findViewById(R.id.schedule_day_idx);
            this.scheduleDate = (TextView) view.findViewById(R.id.schedule_day_date);
            this.scheduleManageCrsBtn = (Button) view.findViewById(R.id.schedule_making_course);
            this.scheduleTravelBtn = (Button) view.findViewById(R.id.schedule_travel);
            this.scheduleStBtn = (Button) view.findViewById(R.id.schedule_start_search);
            this.scheduleDstBtn = (Button) view.findViewById(R.id.schedule_end_search);
            this.scheduleSt = (TextView) view.findViewById(R.id.schedule_start_add);
            this.scheduleDst = (TextView) view.findViewById(R.id.schedule_dst_add);
            scheduleSt.setSelected(true);
            scheduleDst.setSelected(true);
        }
    }


    public ScheduleAdapter(HashMap<String, ScheduleItem> list) {
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

        viewholder.scheduleDayIdx.setText("Day "+mList.get(String.valueOf(position)).getScheduleDayIdx());
        viewholder.scheduleDate.setText(mList.get(String.valueOf(position)).getScheduleDate());

        viewholder.scheduleSt.setText(mList.get(String.valueOf(position)).getScheduleSt());
        viewholder.scheduleDst.setText(mList.get(String.valueOf(position)).getScheduleDst());
        viewholder.scheduleSt.setSelected(true);
        viewholder.scheduleDst.setSelected(true);

        //코스 만들기
        viewholder.scheduleManageCrsBtn.setTag(viewholder.getAdapterPosition());
        viewholder.scheduleManageCrsBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                if(mList.get(String.valueOf(pos)).getScheduleSt() == null){
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Alert");
                    builder.setMessage("You need to choose a place first.");
                    builder.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    return;
                                }
                            });
                    builder.show();
                }
                else{
                    Intent intent = new Intent(v.getContext(), CourseActivity.class);
                    intent.putExtra("courseId",mList.get(String.valueOf(pos)).getScheduleCourseId());
                    intent.putExtra("pos",pos);
                    v.getContext().startActivity(intent);
                }
            }
        });

        //Travel
        viewholder.scheduleTravelBtn.setTag(viewholder.getAdapterPosition());
        viewholder.scheduleTravelBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                if(mList.get(String.valueOf(pos)).getScheduleSt() == null){
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Alert");
                    builder.setMessage("You need to choose a place first.");
                    builder.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    return;
                                }
                            });
                    builder.show();
                }
                else{
                   //TODO: Travel Activity
                    Intent newIntent = new Intent(v.getContext(), com.myseoultrip.MakingCourseActivity.class);
                    newIntent.putExtra("pos",pos);
                    newIntent.putExtra("courseId",mList.get(String.valueOf(pos)).getScheduleCourseId()+"_"+pos);
                    newIntent.putExtra("travelId",mList.get(String.valueOf(pos)).getScheduleCourseId());
                    v.getContext().startActivity(newIntent);
                }
            }
        });

        //출발지 설정
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

        //도착지 설정정
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
