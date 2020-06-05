package com.myseoultravel;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import ru.slybeaver.slycalendarview.SlyCalendarDialog;

public class HomeActivity extends AppCompatActivity implements SlyCalendarDialog.Callback{

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setToolbar();
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
                        Toast.makeText(getApplicationContext(),"예를 선택했습니다.",Toast.LENGTH_LONG).show();
                    }
                });
        builder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"아니오를 선택했습니다.",Toast.LENGTH_LONG).show();
                    }
                });
        builder.show();
    }
}
