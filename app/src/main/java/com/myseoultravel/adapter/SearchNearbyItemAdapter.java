package com.myseoultravel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.myseoultravel.R;

import java.util.ArrayList;

public class SearchNearbyItemAdapter extends RecyclerView.Adapter<SearchNearbyItemAdapter.MyViewHolder> {

    private ArrayList<SearchNearbyItem> mPersons;
    private LayoutInflater mInflate;
    private Context mContext;
    private OnItemClickListener mListener = null ;

    public SearchNearbyItemAdapter(Context context, ArrayList<SearchNearbyItem> persons) {
        this.mContext = context;
        this.mInflate = LayoutInflater.from(context);
        this.mPersons = persons;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.recyclerview_search_nearby_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        //데이터오 뷰를 바인딩
        String url = mPersons.get(position).photo;
        holder.engName.setText(mPersons.get(position).engName);
        Glide.with(mContext)
                .load(url)
                .centerCrop()
                .into(holder.imageView);

//        클릭하면 웹검색하게 하자.
//        holder.search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                클릭시 웹검색하게 하자.
//                String term = mPersons.get(position).name;
//                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
//                intent.putExtra(SearchManager.QUERY, term);
//                mContext.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mPersons.size();
    }


    //ViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView engName;
        public ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        // 리스너 객체의 메서드 호출.
                        if (mListener != null) {
                            mListener.onItemClick(v, pos) ;
                        }
                    }
                }
            });

            engName = (TextView) itemView.findViewById(R.id.shopping_item_eng_name);
            imageView = (ImageView) itemView.findViewById(R.id.shopping_item_imageView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position) ;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }
}
