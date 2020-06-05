package com.myseoultravel.adapter;

import com.myseoultravel.R;

import java.util.ArrayList;

public class AreaData {
    ArrayList<AreaItem> items = new ArrayList<>();

    public ArrayList<AreaItem> getItems(){
        //item 넣기
        ArrayList items = new ArrayList<AreaItem>();

        items.add(new AreaItem(R.drawable.a, "명동/남대문"));
        items.add(new AreaItem(R.drawable.b, "동대문"));
        items.add(new AreaItem(R.drawable.c, "신촌/홍대"));
        items.add(new AreaItem(R.drawable.d, "강남"));
        items.add(new AreaItem(R.drawable.e, "이태원"));

        return items;
    }
}
