package com.myseoultravel;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.archit.calendardaterangepicker.customviews.CalendarListener;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.myseoultravel.adapter.ScheduleAdapter;
import com.myseoultravel.adapter.ScheduleItem;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import ru.slybeaver.slycalendarview.SlyCalendarDialog;

public class ScheduleActivity extends AppCompatActivity {

    private ArrayList<ScheduleItem> mArrayList;
    private ScheduleAdapter mAdapter;
    private int count = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.schedule_recycler_view);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mArrayList = new ArrayList<>();

        mAdapter = new ScheduleAdapter(mArrayList);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        Intent intent = getIntent();
        ArrayList<ScheduleItem> scheduleItems = (ArrayList<ScheduleItem>) intent.getSerializableExtra("item");
        Toast.makeText(getApplicationContext(), scheduleItems.get(0).getScheduleDayIdx()+' '+scheduleItems.get(0).getScheduleDate()+' ', Toast.LENGTH_LONG).show();

        for(int i = 0; i<scheduleItems.size(); i++){
            ScheduleItem data = scheduleItems.get(i);
            mArrayList.add(data);
            count++;
        }
        mAdapter.notifyDataSetChanged();

        setToolbar();
    }

    private void setToolbar() {
        Toolbar scheduleBar = (Toolbar) findViewById(R.id.schedule_toolbar);
        setSupportActionBar(scheduleBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_keyboard_backspace_white_48dp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.scehdule_actionbar_actions, menu) ;
        return true ;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if (resultCode == Activity.RESULT_OK){
                    mArrayList.get(data.getIntExtra("pos",0)).setScheduleSt(data.getStringExtra("targetAdd"));
                    mArrayList.get(data.getIntExtra("pos",0)).setScheduleDst(data.getStringExtra("targetAdd"));
                    mAdapter.notifyDataSetChanged();
            }
        }
        else if(requestCode == 2){
            if (resultCode == Activity.RESULT_OK){
                    mArrayList.get(data.getIntExtra("pos",0)).setScheduleDst(data.getStringExtra("targetAdd"));
                    mAdapter.notifyDataSetChanged();
            }
        }
        else{
            //TODO 결과를 제대로 못 받아왔을 때의 Toast 띄우기

        }
    }
}
