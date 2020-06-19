package com.myseoultrip.adapter;

import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.myseoultrip.R;

import java.util.ArrayList;

public class MakingAdapter extends RecyclerView.Adapter<MakingAdapter.CourseViewHolder> {
    private ArrayList<MakingItem> mList;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String[] lineName = {"Airport Railroad Line"	,
            "Bundang Line"	,
            "Gyeonggang Line"	,
            "Gyeongui-Jungang Line"	,
            "Gyuongchun Line"	,
            "Incheon Line1"	,
            "Incheon Line2"	,
            "LINE 1"	,
            "LINE 2"	,
            "LINE 3"	,
            "LINE 4"	,
            "LINE 5"	,
            "LINE 6"	,
            "LINE 7"	,
            "LINE 8"	,
            "LINE 9"	,
            "Shinbundang Line"	,
            "Suin Line"	,
            "Ui Lrt Line"	,
            "Uijeongbu Lrt Line"	,
            "Yongin Everline"
    };
    public class CourseViewHolder extends RecyclerView.ViewHolder{
        private TextView poiNameSt;
        private TextView poiIdxSt;
        private TextView subwayLineSt;
        private TextView stationMixNameSt;
        private ImageView subwayImgSt;
        private TextView poiNameDst;
        private TextView poiIdxDst;
        private TextView subwayLineDst;
        private TextView stationMixNameDst;
        private ImageView subwayImgDst;
        private Button detailBtn;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            this.poiNameSt = (TextView) itemView.findViewById(R.id.making_poi_name_st);
            this.poiIdxSt = (TextView) itemView.findViewById(R.id.making_poi_idx_st);
            this.subwayLineSt = (TextView) itemView.findViewById(R.id.making_subway_line_st);
            this.stationMixNameSt = (TextView) itemView.findViewById(R.id.making_subway_name_st);
            this.subwayImgSt = (ImageView) itemView.findViewById(R.id.making_subway_line_image_st);

            this.poiNameDst = (TextView) itemView.findViewById(R.id.making_poi_name_dst);
            this.poiIdxDst = (TextView) itemView.findViewById(R.id.making_poi_idx_dst);
            this.subwayLineDst = (TextView) itemView.findViewById(R.id.making_subway_line_dst);
            this.stationMixNameDst = (TextView) itemView.findViewById(R.id.making_subway_name_dst);
            this.subwayImgDst = (ImageView) itemView.findViewById(R.id.making_subway_line_image_dst);

            this.detailBtn = (Button) itemView.findViewById(R.id.making_detail_button);
        }
    }

    public MakingAdapter(ArrayList<MakingItem> list){this.mList = list;}

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
        holder.poiNameSt.setText(mList.get(position).getPoiNameSt());
        holder.poiNameSt.setHorizontallyScrolling(true);
        holder.poiNameSt.setSingleLine(true);
        holder.poiNameSt.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        holder.poiNameSt.setSelected(true);
        holder.poiNameSt.setMarqueeRepeatLimit(-1);

        holder.poiIdxSt.setText(String.valueOf(mList.get(position).getPoiIdxSt())+".");
        holder.subwayLineSt.setText(mList.get(position).getSubwayLineSt());
        holder.stationMixNameSt.setText(mList.get(position).getStationMixNameSt());

        holder.poiNameDst.setText(mList.get(position).getPoiNameDst());
        holder.poiNameDst.setHorizontallyScrolling(true);
        holder.poiNameDst.setSingleLine(true);
        holder.poiNameDst.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        holder.poiNameDst.setSelected(true);
        holder.poiNameDst.setMarqueeRepeatLimit(-1);

        holder.poiIdxDst.setText(String.valueOf(mList.get(position).getPoiIdxDst())+".");
        holder.subwayLineDst.setText(mList.get(position).getSubwayLineSt());
        holder.stationMixNameDst.setText(mList.get(position).getStationMixNameDst());

        holder.detailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle(mList.get(position).getTime());
                    builder.setMessage(mList.get(position).getDetailCourse());
                    builder.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    builder.show();
            }
        });

        for(int i = 0; i<lineName.length; i++){
            if(mList.get(position).getSubwayLineSt().equals(lineName[0])){
                holder.subwayImgSt.setImageResource(R.drawable.arexline_icon);
            }
            else if(mList.get(position).getSubwayLineSt().equals(lineName[1])){
                holder.subwayImgSt.setImageResource(R.drawable.bundangline_icon);
            }
            else if(mList.get(position).getSubwayLineSt().equals(lineName[2])){
                holder.subwayImgSt.setImageResource(R.drawable.gyeonggangline_icon);
            }
            else if(mList.get(position).getSubwayLineSt().equals(lineName[3])){
                holder.subwayImgSt.setImageResource(R.drawable.glline_icon);
            }
            else if(mList.get(position).getSubwayLineSt().equals(lineName[4])){
                holder.subwayImgSt.setImageResource(R.drawable.gyeongchunline_icon);
            }
            else if(mList.get(position).getSubwayLineSt().equals(lineName[5])){
                holder.subwayImgSt.setImageResource(R.drawable.incheonmetro1_icon);
            }
            else if(mList.get(position).getSubwayLineSt().equals(lineName[6])){
                holder.subwayImgSt.setImageResource(R.drawable.incheonmetro2_icon);
            }
            else if(mList.get(position).getSubwayLineSt().equals(lineName[7])){
                holder.subwayImgSt.setImageResource(R.drawable.seoulmetro1_icon);
            }
            else if(mList.get(position).getSubwayLineSt().equals(lineName[8])){
                holder.subwayImgSt.setImageResource(R.drawable.seoulmetro2_icon);
            }
            else if(mList.get(position).getSubwayLineSt().equals(lineName[9])){
                holder.subwayImgSt.setImageResource(R.drawable.seoulmetro3_icon);
            }
            else if(mList.get(position).getSubwayLineSt().equals(lineName[10])){
                holder.subwayImgSt.setImageResource(R.drawable.seoulmetro4_icon);
            }
            else if(mList.get(position).getSubwayLineSt().equals(lineName[11])){
                holder.subwayImgSt.setImageResource(R.drawable.seoulmetro5_icon);
            }
            else if(mList.get(position).getSubwayLineSt().equals(lineName[12])){
                holder.subwayImgSt.setImageResource(R.drawable.seoulmetro6_icon);
            }
            else if(mList.get(position).getSubwayLineSt().equals(lineName[13])){
                holder.subwayImgSt.setImageResource(R.drawable.seoulmetro7_icon);
            }
            else if(mList.get(position).getSubwayLineSt().equals(lineName[14])){
                holder.subwayImgSt.setImageResource(R.drawable.seoulmetro8_icon);
            }
            else if(mList.get(position).getSubwayLineSt().equals(lineName[15])){
                holder.subwayImgSt.setImageResource(R.drawable.seoulmetro9_icon);
            }
            else if(mList.get(position).getSubwayLineSt().equals(lineName[16])){
                holder.subwayImgSt.setImageResource(R.drawable.dxline_icon);
            }
            else if(mList.get(position).getSubwayLineSt().equals(lineName[17])){
                holder.subwayImgSt.setImageResource(R.drawable.suinline_icon);
            }
            else if(mList.get(position).getSubwayLineSt().equals(lineName[18])){
                holder.subwayImgSt.setImageResource(R.drawable.uiline_icon);
            }
            else if(mList.get(position).getSubwayLineSt().equals(lineName[19])){
                holder.subwayImgSt.setImageResource(R.drawable.uline_icon);
            }
            else if(mList.get(position).getSubwayLineSt().equals(lineName[20])){
                holder.subwayImgSt.setImageResource(R.drawable.everline_icon);
            }
            if(mList.get(position).getSubwayLineDst().equals(lineName[0])){
                holder.subwayImgDst.setImageResource(R.drawable.arexline_icon);
            }
            else if(mList.get(position).getSubwayLineDst().equals(lineName[1])){
                holder.subwayImgDst.setImageResource(R.drawable.bundangline_icon);
            }
            else if(mList.get(position).getSubwayLineDst().equals(lineName[2])){
                holder.subwayImgDst.setImageResource(R.drawable.gyeonggangline_icon);
            }
            else if(mList.get(position).getSubwayLineDst().equals(lineName[3])){
                holder.subwayImgDst.setImageResource(R.drawable.glline_icon);
            }
            else if(mList.get(position).getSubwayLineDst().equals(lineName[4])){
                holder.subwayImgDst.setImageResource(R.drawable.gyeongchunline_icon);
            }
            else if(mList.get(position).getSubwayLineDst().equals(lineName[5])){
                holder.subwayImgDst.setImageResource(R.drawable.incheonmetro1_icon);
            }
            else if(mList.get(position).getSubwayLineDst().equals(lineName[6])){
                holder.subwayImgDst.setImageResource(R.drawable.incheonmetro2_icon);
            }
            else if(mList.get(position).getSubwayLineDst().equals(lineName[7])){
                holder.subwayImgDst.setImageResource(R.drawable.seoulmetro1_icon);
            }
            else if(mList.get(position).getSubwayLineDst().equals(lineName[8])){
                holder.subwayImgDst.setImageResource(R.drawable.seoulmetro2_icon);
            }
            else if(mList.get(position).getSubwayLineDst().equals(lineName[9])){
                holder.subwayImgDst.setImageResource(R.drawable.seoulmetro3_icon);
            }
            else if(mList.get(position).getSubwayLineDst().equals(lineName[10])){
                holder.subwayImgDst.setImageResource(R.drawable.seoulmetro4_icon);
            }
            else if(mList.get(position).getSubwayLineDst().equals(lineName[11])){
                holder.subwayImgDst.setImageResource(R.drawable.seoulmetro5_icon);
            }
            else if(mList.get(position).getSubwayLineDst().equals(lineName[12])){
                holder.subwayImgDst.setImageResource(R.drawable.seoulmetro6_icon);
            }
            else if(mList.get(position).getSubwayLineDst().equals(lineName[13])){
                holder.subwayImgDst.setImageResource(R.drawable.seoulmetro7_icon);
            }
            else if(mList.get(position).getSubwayLineDst().equals(lineName[14])){
                holder.subwayImgDst.setImageResource(R.drawable.seoulmetro8_icon);
            }
            else if(mList.get(position).getSubwayLineDst().equals(lineName[15])){
                holder.subwayImgDst.setImageResource(R.drawable.seoulmetro9_icon);
            }
            else if(mList.get(position).getSubwayLineDst().equals(lineName[16])){
                holder.subwayImgDst.setImageResource(R.drawable.dxline_icon);
            }
            else if(mList.get(position).getSubwayLineDst().equals(lineName[17])){
                holder.subwayImgDst.setImageResource(R.drawable.suinline_icon);
            }
            else if(mList.get(position).getSubwayLineDst().equals(lineName[18])){
                holder.subwayImgDst.setImageResource(R.drawable.uiline_icon);
            }
            else if(mList.get(position).getSubwayLineDst().equals(lineName[19])){
                holder.subwayImgDst.setImageResource(R.drawable.uline_icon);
            }
            else if(mList.get(position).getSubwayLineDst().equals(lineName[20])){
                holder.subwayImgDst.setImageResource(R.drawable.everline_icon);
            }
        }
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
