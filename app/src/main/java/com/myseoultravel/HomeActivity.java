package com.myseoultravel;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myseoultravel.adapter.ScheduleAdapter;
import com.myseoultravel.adapter.ScheduleItem;
import com.myseoultravel.adapter.TravelAdapter;
import com.myseoultravel.adapter.TravelItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.Locale;

import ru.slybeaver.slycalendarview.SlyCalendarDialog;

public class HomeActivity extends AppCompatActivity implements SlyCalendarDialog.Callback{

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    public ArrayList<TravelItem> travelArrayList;
    public TravelAdapter travelAdapter;
    public int travelCount = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setToolbar();

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.home_recycler_view);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        travelArrayList = new ArrayList<>();

        travelAdapter = new TravelAdapter(travelArrayList);
        mRecyclerView.setAdapter(travelAdapter);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                TravelItem travelItem = travelArrayList.get(position);
                //Toast.makeText(getApplicationContext(), travelItem.getTravelIdx()+' '+travelItem.getTravelStartDate()+' '+travelItem.getTravelEndDate(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), ScheduleActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                ArrayList<ScheduleItem> scheduleItems = travelItem.getScheduleItems();

                intent.putExtra("item", scheduleItems);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
    }

    private void setToolbar() {
        Toolbar homeToolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(homeToolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowCustomEnabled(true);
        actionbar.setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_actionbar_actions, menu);
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add :
                // TODO : process the click event for action_search item.
                // TODO : 날짜얻고, 날짜만큼의 리스트 만든다.
                launchCalendar();

                return true ;
            case R.id.action_exit:
                long tempTime = System.currentTimeMillis();
                long intervalTime = tempTime - backPressedTime;

                if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
                    super.onBackPressed();
                }
                else {
                    backPressedTime = tempTime;
                    Toast.makeText(getApplicationContext(), "Please click EXIT again to exit.", Toast.LENGTH_SHORT).show();
                }
                return true;
            default :
                return super.onOptionsItemSelected(item) ;
        }
    }

    public void launchCalendar() {
        new SlyCalendarDialog()
                .setSingle(false)
                .setFirstMonday(false)
                .setHeaderColor(Color.parseColor("#941e34"))
                .setSelectedColor(Color.parseColor("#6789ca"))
                .setCallback(HomeActivity.this)
                .show(getSupportFragmentManager(), "TAG_SLYCALENDAR");
    }

    @Override
    public void onCancelled() {

    }

    @Override
    public void onDataSelected(Calendar firstDate, Calendar secondDate, int hours, int minutes) {
        if (firstDate != null) {
            if (secondDate == null) {
                dialogShowNotSelected();
            } else {
                dialogShowSelected(firstDate, secondDate);
            }
        }
        else{
            dialogShowNotSelected();
        }
    }

    public void dialogShowNotSelected() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert");
        builder.setMessage("Please select both a start and end date.");
        builder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        launchCalendar();
                    }
                });
        builder.show();
    }

    public void dialogShowSelected(Calendar firstDate, Calendar secondDate)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Is the date you selected correct?");
        builder.setMessage(getString(
                                R.string.period,
                                new SimpleDateFormat(getString(R.string.dateFormat), Locale.getDefault()).format(firstDate.getTime()),
                                new SimpleDateFormat(getString(R.string.dateFormat), Locale.getDefault()).format(secondDate.getTime())
                        ));
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        ArrayList<ScheduleItem> scheduleItemArrayList = new ArrayList<>();
                        Calendar tempCal = (Calendar)firstDate.clone();
                        int idx = 1;
                        while(!tempCal.after(secondDate)){
                            ScheduleItem data = new ScheduleItem("Day"+idx++,new SimpleDateFormat(getString(R.string.dateFormat), Locale.getDefault()).format(tempCal.getTime()));
                            tempCal.add(Calendar.DATE,1);
                            scheduleItemArrayList.add(data);
                        }

                        TravelItem travelItem = new TravelItem("Travel "+String.valueOf(2+(travelCount++)),
                                new SimpleDateFormat(getString(R.string.dateFormat), Locale.getDefault()).format(firstDate.getTime()),
                                new SimpleDateFormat(getString(R.string.dateFormat), Locale.getDefault()).format(secondDate.getTime()), scheduleItemArrayList);
                        travelArrayList.add(travelItem);
                        travelAdapter.notifyDataSetChanged();
                    }
                });
        builder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Task was cancelled.",Toast.LENGTH_LONG).show();
                    }
                });
        builder.show();
    }

    //ItemListener
    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private HomeActivity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final HomeActivity.ClickListener clickListener) {
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
