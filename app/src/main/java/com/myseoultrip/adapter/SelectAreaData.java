package com.myseoultavel.adapter;

import com.myseoultrip.R;

import java.util.ArrayList;

public class SelectAreaData {
    ArrayList<SelectAreaItem> items = new ArrayList<>();

    public ArrayList<SelectAreaItem> getItems(){
        //item 넣기
        ArrayList items = new ArrayList<SelectAreaItem>();

        items.add(new SelectAreaItem(R.drawable.a, "명동/남대문"));
        items.add(new SelectAreaItem(R.drawable.b, "동대문"));
        items.add(new SelectAreaItem(R.drawable.c, "신촌/홍대"));
        items.add(new SelectAreaItem(R.drawable.d, "강남"));
        items.add(new SelectAreaItem(R.drawable.e, "이태원"));

        return items;
    }
}
