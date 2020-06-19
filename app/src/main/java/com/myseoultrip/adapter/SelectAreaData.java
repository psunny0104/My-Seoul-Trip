package com.myseoultrip.adapter;

import com.myseoultrip.R;

import java.util.ArrayList;

public class SelectAreaData {
    ArrayList<SelectAreaItem> items = new ArrayList<>();

    public ArrayList<SelectAreaItem> getItems(){
        //item 넣기
        ArrayList items = new ArrayList<SelectAreaItem>();

        items.add(new SelectAreaItem(R.drawable.img_myeongdong, "Myeong-dong(명동)/Namdaemun(남대문)"));
        items.add(new SelectAreaItem(R.drawable.img_dongdaemun, "Dongdaemun(동대문)"));
        items.add(new SelectAreaItem(R.drawable.img_sinchon, "Sinchon(신촌)/홍대(Hongdae)"));
        items.add(new SelectAreaItem(R.drawable.img_gangnam, "Gangnam(강남)"));
        items.add(new SelectAreaItem(R.drawable.img_itaewon, "Itaewon(이태원)"));

        return items;
    }
}
