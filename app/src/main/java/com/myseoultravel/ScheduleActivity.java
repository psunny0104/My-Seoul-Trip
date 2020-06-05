package com.myseoultravel;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.archit.calendardaterangepicker.customviews.CalendarListener;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import ru.slybeaver.slycalendarview.SlyCalendarDialog;

public class ScheduleActivity extends AppCompatActivity implements SlyCalendarDialog.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);



//        next.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                changeActivity(v);
//            }
//        });

        setToolbar();

//        new SlyCalendarDialog()
//                .setSingle(false)
//                .setCallback(ScheduleActivity.this)
//                .setBackgroundColor(Color.parseColor("#ff0000"))
//                .setSelectedTextColor(Color.parseColor("#ffff00"))
//                .setSelectedColor(Color.parseColor("#0000ff"))
//                .show(getSupportFragmentManager(), "TAG_SLYCALENDAR");
    }

    private void setToolbar() {
        Toolbar homeToolbar = (Toolbar) findViewById(R.id.schedule_toolbar);
        setSupportActionBar(homeToolbar);
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
    public void onCancelled() {

    }

    @Override
    public void onDataSelected(Calendar firstDate, Calendar secondDate, int hours, int minutes) {
        if (firstDate != null) {
            if (secondDate == null) {
                firstDate.set(Calendar.HOUR_OF_DAY, hours);
                firstDate.set(Calendar.MINUTE, minutes);
                Toast.makeText(
                        this,
                        new SimpleDateFormat(getString(R.string.timeFormat), Locale.getDefault()).format(firstDate.getTime()),
                        Toast.LENGTH_LONG

                ).show();
            } else {
                Toast.makeText(
                        this,
                        getString(
                                R.string.period,
                                new SimpleDateFormat(getString(R.string.dateFormat), Locale.getDefault()).format(firstDate.getTime()),
                                new SimpleDateFormat(getString(R.string.timeFormat), Locale.getDefault()).format(secondDate.getTime())
                        ),
                        Toast.LENGTH_LONG

                ).show();
            }
        }
    }

    /**
     * Calender
     */


//    public void changeActivity( View v ) {
//        switch( v.getId() ) {
//            default:
//            case R.id.next: {
//                Intent intent = new Intent(this, SelectAreaActivity.class);
//                startActivity(intent);
//                break;
//            }
//        }
//    }
}
