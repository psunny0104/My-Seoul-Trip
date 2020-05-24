package com.myseoultravel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myseoultravel.R;

import java.util.ArrayList;

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.ViewHolder> {
    private ArrayList<AreaItem> items = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_area_item, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(AreaAdapter.ViewHolder viewHolder, int position) {
        AreaItem item = items.get(position);

        viewHolder.imageButton.setImageResource(item.getAreaImage());
        viewHolder.textView.setText(item.getAreaTitle());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public void setItems(ArrayList<AreaItem> items){
        this.items = items;
    }

    public ArrayList<AreaItem> getItems() {
        return items;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageButton imageButton;
        public TextView textView;

        public ViewHolder(View view) {
            super(view);
            imageButton = view.findViewById(R.id.area_image);
            textView = view.findViewById(R.id.area_title);

        }
    }
}
