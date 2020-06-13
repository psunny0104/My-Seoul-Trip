package com.myseoultravel;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.myseoultravel.adapter.SelectAreaAdapter;
import com.myseoultravel.adapter.SelectAreaData;
import com.myseoultravel.adapter.SelectAreaItem;

import java.util.ArrayList;

public class SelectAreaActivity extends AppCompatActivity {

    SelectAreaAdapter adapter = new SelectAreaAdapter();
    RecyclerView recyclerView;
    ArrayList arrayList = new SelectAreaData().getItems();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        setToolbar();

        Intent intent = getIntent();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setItems(arrayList);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                SelectAreaItem selectAreaItem = (SelectAreaItem) arrayList.get(position);
                System.out.println(position);
                Intent newIntent = new Intent(getApplicationContext(), SearchNearbyActivity.class);
                newIntent.putExtra("name", selectAreaItem.getAreaTitle());
                newIntent.putExtra("courseId", intent.getStringExtra("courseId"));
                newIntent.putExtra("pos",intent.getIntExtra("pos",0));
                newIntent.putExtra("poiCount",intent.getIntExtra("poiCount",0));
                Log.i("myTag","Area: "+intent.getStringExtra("courseId"));
                //postion에 따른 좌표 분기
                if(position == 0){
                    newIntent.putExtra("coords", "37.560892, 126.986168");
                    newIntent.putExtra("mapX", 126.986168);
                    newIntent.putExtra("mapY", 37.560892);
                }
                else if(position == 1){
                    newIntent.putExtra("coords", "37.564687, 127.004912");
                    newIntent.putExtra("mapX", 127.004912);
                    newIntent.putExtra("mapY", 37.564687);
                }
                else if(position == 2){
                    newIntent.putExtra("coords", "37.555247, 126.936692");
                    newIntent.putExtra("mapX", 126.936692);
                    newIntent.putExtra("mapY", 37.555247);
                }
                else if(position == 3){
                    newIntent.putExtra("coords", "37.497900, 127.027547");
                    newIntent.putExtra("mapX", 127.027547);
                    newIntent.putExtra("mapY", 37.497900);
                }
                else if(position == 4){
                    newIntent.putExtra("coords", "37.534594, 126.994220");
                    newIntent.putExtra("mapX", 126.994220);
                    newIntent.putExtra("mapY", 37.534594);
                }
                newIntent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
                startActivity(newIntent);
                finish();
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

    }

    private void setToolbar() {
        Toolbar scheduleBar = (Toolbar) findViewById(R.id.select_toolbar);
        setSupportActionBar(scheduleBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_keyboard_backspace_white_48dp);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                // TODO : process the click event for action_search item.
                onBackPressed();
                return true ;
            // ...
            // ...
            default :
                return super.onOptionsItemSelected(item) ;
        }
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private SelectAreaActivity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final SelectAreaActivity.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }
}
