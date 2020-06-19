package com.myseoultrip;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.myseoultrip.adapter.CourseAdapter;
import com.myseoultrip.adapter.CourseItem;
import com.myseoultrip.adapter.PoiItem;
import com.myseoultrip.adapter.ScheduleItem;
import com.myseoultrip.model.place.tour.CategoryCodeModel;

import java.util.ArrayList;

public class CourseActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    String courseId = "";
    int pos;
    ScheduleItem scheduleItem;
    CourseItem courseItem;
    int poiCount = 0;
    int idxCount = 0;
    ArrayList<PoiItem> poiItems;
    CourseAdapter courseAdapter;
    CategoryCodeModel categoryCodeModel = new CategoryCodeModel();
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        setToolbar();

        categoryCodeModel.init();
        mAuth = FirebaseAuth.getInstance();

        mRecyclerView = (RecyclerView) findViewById(R.id.course_recycler);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        poiItems = new ArrayList<>();
        courseAdapter = new CourseAdapter(poiItems);

        Intent intent = getIntent();
        Log.i("myTag","Course: "+intent.getStringExtra("courseId"));
        courseId = intent.getStringExtra("courseId");
        pos = intent.getIntExtra("pos",0);

        getScheduleDb();
        courseAdapter.notifyDataSetChanged();

        mRecyclerView.setAdapter(courseAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        Button selectAreaBtn = (Button) findViewById(R.id.course_item_add);
        Button makeBtn = (Button) findViewById(R.id.course_make);

        selectAreaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(getApplicationContext(), com.myseoultrip.SelectAreaActivity.class);
                newIntent.putExtra("courseId",courseId);
                newIntent.putExtra("pos",pos);
                newIntent.putExtra("poiCount",poiCount);
                startActivityForResult(newIntent,100);
            }
        });
        courseAdapter.notifyDataSetChanged();

        makeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(getApplicationContext(), com.myseoultrip.MakingCourseActivity.class);
                newIntent.putExtra("pos",pos);
                newIntent.putExtra("courseId",courseId+"_"+pos);
                newIntent.putExtra("travelId",courseId);
                startActivityForResult(newIntent,200);
            }
        });
    }

    private void setToolbar() {
        Toolbar scheduleBar = (Toolbar) findViewById(R.id.course_toolbar);
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

    private void getScheduleDb() {
        Log.i("myTag","Course: "+pos);
        db.collection("course").document(courseId+"_"+pos)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Log.i("myTag", "Course: "+courseId);
                        courseItem = (CourseItem) documentSnapshot.toObject(CourseItem.class);
                        //poiCount = courseItem.getPoiItemHashMap().size();
                        Log.d("myTag", "Course: " + courseItem.getTravelItemId());

                        TextView courseDayIdx = (TextView) findViewById(R.id.course_day_idx);
                        TextView courseDayDate = (TextView) findViewById(R.id.course_day_date);
                        TextView courseStAdd = (TextView) findViewById(R.id.course_start_add);
                        TextView courseDstAdd = (TextView) findViewById(R.id.course_dst_add);
                        courseDayIdx.setText("Day "+courseItem.getCourseDayIdx());
                        courseDayDate.setText(courseItem.getCourseDate());
                        courseDstAdd.setText(courseItem.getCourseDstAdd());
                        courseStAdd.setText(courseItem.getCourseStAdd());

                        db.collection("poi").whereEqualTo("courseItemId",courseId+"_"+pos)
                                                .get()
                                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                    @Override
                                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                        poiCount = queryDocumentSnapshots.size();

                                                        Log.i("myTag","Course: poiCount: "+poiCount);
                                        if(poiCount != 0){
                                            for(int i = 0; i<poiCount; i++){
                                                PoiItem poiItem = queryDocumentSnapshots.getDocuments().get(i).toObject(PoiItem.class);
                                                poiItems.add(poiItem);
                                            }
                                            courseAdapter.notifyDataSetChanged();
                                        }
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w("myTag", "Course: Error getting document", e);
                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("myTag", "Course: Error getting document", e);
                    }
                });

        //idx 체크
        for(int curCount = 1; curCount<=poiCount; curCount++) {
            int finalCurCount = curCount;
            db.collection("poi").document(courseId + "_" + pos + "_" + finalCurCount)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                if (task.getResult().getData() != null){
                                    Log.i("myTag", "Course: already exits idx: "+ finalCurCount +" / poiCount"+poiCount);
                                }
                                else{
                                    idxCount = finalCurCount;
                                }
                            }
                        }
                    });
            if(idxCount == finalCurCount){
                break;
            }

        }
        Log.i("myTag", "Course: getTagId/ idxCount: "+idxCount+", poiCount: "+poiCount);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100){
            Log.i("myTag", "Course: PoiItem received!");
            if(resultCode == 100){
                PoiItem poiItem = (PoiItem) data.getSerializableExtra("poiItem");
                Log.i("myTag", "Course: "+poiItem.getPoiTitle());

                db.collection("poi").whereEqualTo("courseItemId",courseId+"_"+pos)
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                poiCount = queryDocumentSnapshots.size();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("myTag", "Course: Error getting document", e);
                            }
                        });

                idxCount = poiCount;
                //idx 체크
                for(int curCount = 1; curCount<=poiCount; curCount++) {
                    int finalCurCount = curCount;
                    db.collection("poi").document(courseId + "_" + pos + "_" + finalCurCount)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        if (task.getResult().getData() != null){
                                            Log.i("myTag", "Course: already exits idx: "+ finalCurCount +" / poiCount"+poiCount);
                                        }
                                        else{
                                            idxCount = finalCurCount;
                                        }
                                    }
                                }
                            });
                    if(idxCount == finalCurCount){
                        break;
                    }

                }


                poiItem.setTravelItemId(courseId);
                poiItem.setCourseItemId(courseId+"_"+pos);
                poiItem.setPoiIdx(++idxCount);
                poiItems.add(poiItem);
                poiCount++;
                courseAdapter.notifyDataSetChanged();
                Log.i("myTag","Course/ poiCount: "+poiCount+"/idxCount: "+idxCount);
                db.collection("poi").document(courseId+"_"+pos+"_"+idxCount)
//                db.collection("poi").document(courseId+"_"+pos+"_"+(data.getIntExtra("poiCount",0)+1))
                        .set(poiItem)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("myTag", "Course: DocumentSnapshot successfully added!");

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("myTag", "Course: Error adding document", e);

                            }
                        });
                courseAdapter.notifyDataSetChanged();
            }
        }
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }
}
