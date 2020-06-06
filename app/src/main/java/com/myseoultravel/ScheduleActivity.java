package com.myseoultravel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.myseoultravel.adapter.ScheduleAdapter;
import com.myseoultravel.adapter.ScheduleItem;
import com.myseoultravel.adapter.TravelItem;

import java.util.ArrayList;
import java.util.HashMap;

public class ScheduleActivity extends AppCompatActivity {

    private ArrayList<ScheduleItem> scheduleArrayList;
    private ScheduleAdapter scheduleAdapter;
    private int count = -1;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    private String travelId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        mAuth = FirebaseAuth.getInstance();

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.schedule_recycler_view);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        scheduleArrayList = new ArrayList<>();

        scheduleAdapter = new ScheduleAdapter(scheduleArrayList);
        mRecyclerView.setAdapter(scheduleAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        Intent intent = getIntent();
        travelId = intent.getStringExtra("travelId");

        getScheduleDb();
        scheduleAdapter.notifyDataSetChanged();

        setToolbar();
    }

    private void getScheduleDb() {
        Log.d("myTag", "Schedule: " + travelId);
        db.collection("travel").document(travelId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        TravelItem travelItem = documentSnapshot.toObject(TravelItem.class);
                        for (int i = 0; i < travelItem.getScheduleItems().size(); i++) {
                            Log.d("myTag", "Schedule: " + travelItem.getScheduleItems().get(i).getScheduleDayIdx());
                            scheduleArrayList.add(travelItem.getScheduleItems().get(i));
                            count++;
                        }
                        scheduleAdapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("myTag", "Home: Error getting document", e);
                    }
                });
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
                    int pos = data.getIntExtra("pos",0);
                    String add = data.getStringExtra("targetAdd");

//                    db.collection("travel").document(travelId)
//                            .set({"scheduleItems":})
//                            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//                                    Log.d("myTag", "Schedule: DocumentSnapshot successfully updated!");
//                                }
//                            });
//                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                                @Override
//                                public void onSuccess(DocumentSnapshot documentSnapshot) {
////                                    ScheduleItem scheduleItem = documentSnapshot.toObject(ScheduleItem.class);
//                                    Log.d("myTag", "Schedule: " + documentSnapshot.getData());
//                                }
//                            });
//                    db.collection("travel").document(travelId)
//                            .update("scheduleItems."+pos+".scheduleSt",add,
//                                    "scheduleItems."+pos+".scheduleDst",add)
//                            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//                                    scheduleArrayList.get(pos).setScheduleSt(add);
//                                    scheduleArrayList.get(pos).setScheduleDst(add);
//                                    scheduleAdapter.notifyDataSetChanged();
//                                    Log.d("myTag", "Schedule: DocumentSnapshot successfully updated!");
//                                }
//                            })
//                            .addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    Log.w("myTag", "Schedule: Error updating document", e);
//                                }
//                            });
            }
        }
        else if(requestCode == 2){
            if (resultCode == Activity.RESULT_OK){
                int pos = data.getIntExtra("pos",0);
                String add = data.getStringExtra("targetAdd");

                db.collection("travel").document(travelId)
                        .update("scheduleItems."+pos+".scheduleDst",add)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                scheduleArrayList.get(pos).setScheduleDst(add);
                                scheduleAdapter.notifyDataSetChanged();
                                Log.d("myTag", "Schedule: DocumentSnapshot successfully updated!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("myTag", "Schedule: Error updating document", e);
                            }
                        });
            }
        }
        else{
            //TODO 결과를 제대로 못 받아왔을 때의 Toast 띄우기

        }
    }
}
