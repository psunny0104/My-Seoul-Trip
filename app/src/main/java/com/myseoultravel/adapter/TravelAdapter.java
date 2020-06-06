package com.myseoultravel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myseoultravel.R;

import java.util.ArrayList;

public class TravelAdapter extends RecyclerView.Adapter<TravelAdapter.TravelViewHolder>{
    private ArrayList<TravelItem> mList;

    public class TravelViewHolder extends RecyclerView.ViewHolder {
        private TextView travelIdx;
        private TextView travelStartDate;
        private TextView travelEndDate;

        public TravelViewHolder(View view) {
            super(view);
            this.travelIdx = (TextView) view.findViewById(R.id.home_idx);
            this.travelStartDate = (TextView) view.findViewById(R.id.home_start_date);
            this.travelEndDate = (TextView) view.findViewById(R.id.home_end_date);
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

        viewholder.travelIdx.setText(mList.get(position).getTravelIdx());
        viewholder.travelStartDate.setText(mList.get(position).getTravelStartDate());
        viewholder.travelEndDate.setText(mList.get(position).getTravelEndDate());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}
