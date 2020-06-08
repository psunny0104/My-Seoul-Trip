package com.myseoultravel;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;
import com.myseoultravel.adapter.ScheduleAdapter;
import com.myseoultravel.adapter.ScheduleItem;
import com.myseoultravel.adapter.TravelAdapter;
import com.myseoultravel.adapter.TravelItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import ru.slybeaver.slycalendarview.SlyCalendarDialog;

public class HomeActivity extends AppCompatActivity implements SlyCalendarDialog.Callback{

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    public ArrayList<TravelItem> travelArrayList;
    public TravelAdapter travelAdapter;
    public int travelCount = 1;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setToolbar();

        mAuth = FirebaseAuth.getInstance();

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.home_recycler_view);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        travelArrayList = new ArrayList<>();
        travelAdapter = new TravelAdapter(travelArrayList);

        //db
        getTravelDb();
        travelAdapter.notifyDataSetChanged();

        mRecyclerView.setAdapter(travelAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    public void getTravelDb() {
        db.collection("travel")
                .whereEqualTo("userId",mAuth.getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            travelCount = task.getResult().size();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("myTag", "Home: "+document.getId() + " => " + document.getData());

                                //TravelItem 저장
                                TravelItem travelItem = new TravelItem(document.getId(),document.getData().get("userId").toString(),
                                        document.getData().get("travelIdx").toString(),
                                        document.getData().get("travelStartDate").toString(),
                                        document.getData().get("travelEndDate").toString()
                                        );
                                travelArrayList.add(travelItem);
                            }
                            travelAdapter.notifyDataSetChanged();
                        } else {
                            Log.w("myTag", "Home: Error getting documents.", task.getException());
                        }
                    }
                });
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
                dialogShowSelected(firstDate, firstDate);
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

                        HashMap<String, ScheduleItem> scheduleItemArrayList = new HashMap<>();
                        Calendar tempCal = (Calendar)firstDate.clone();
                        int idx = 0;
                        while(!tempCal.after(secondDate)){
                            ScheduleItem data = new ScheduleItem("Day"+(1+idx), new SimpleDateFormat(getString(R.string.dateFormat), Locale.getDefault()).format(tempCal.getTime()), "");
                            tempCal.add(Calendar.DATE,1);
                            scheduleItemArrayList.put(String.valueOf(idx++),data);
                        }

                        TravelItem travelItem = new TravelItem(mAuth.getCurrentUser().getUid(),"Travel "+String.valueOf(++travelCount),
                                new SimpleDateFormat(getString(R.string.dateFormat), Locale.getDefault()).format(firstDate.getTime()),
                                new SimpleDateFormat(getString(R.string.dateFormat), Locale.getDefault()).format(secondDate.getTime()), scheduleItemArrayList);

                        //firestore 연동
                        int finalIdx = idx;
                        db.collection("travel")
                                .add(travelItem)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Log.d("myTag", "Home: DocumentSnapshot added with ID: " + documentReference.getId());
                                        travelItem.setTravelId(documentReference.getId());
                                        travelArrayList.add(travelItem);
                                        travelAdapter.notifyDataSetChanged();
                                        db.collection("travel").document(documentReference.getId())
                                                .update("travelId",documentReference.getId());
                                        for(int i = finalIdx-1; i>=0; i--){
                                            db.collection("travel").document(documentReference.getId())
                                                    .update("scheduleItems."+i+".scheduleCourseId",documentReference.getId());
                                        }
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w("myTag", "Home: Error adding document", e);
                                    }
                                });
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

}
