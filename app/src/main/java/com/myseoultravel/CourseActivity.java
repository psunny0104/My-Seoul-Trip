package com.myseoultravel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.myseoultravel.adapter.CourseAdapter;
import com.myseoultravel.adapter.CourseItem;
import com.myseoultravel.adapter.PoiItem;
import com.myseoultravel.adapter.ScheduleAdapter;
import com.myseoultravel.adapter.ScheduleItem;
import com.myseoultravel.adapter.TravelItem;
import com.myseoultravel.model.place.tour.CategoryCodeModel;

import java.util.HashMap;

public class CourseActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    String courseId = "";
    int pos;
    ScheduleItem scheduleItem;
    CourseItem courseItem;
    int courseCount = 0;
    HashMap<String, PoiItem> poiItemHashMap;
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

        poiItemHashMap = new HashMap<>();
        courseAdapter = new CourseAdapter(poiItemHashMap);

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
        selectAreaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(getApplicationContext(),SelectAreaActivity.class);
                newIntent.putExtra("courseId",courseId);
                newIntent.putExtra("pos",pos);
                startActivityForResult(newIntent,100);
            }
        });
        courseAdapter.notifyDataSetChanged();

//        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new ClickListener() {
//
//            @Override
//            public void onClick(View view, int position) {
//                Intent intent = new Intent(getApplicationContext(), SearchDetailActivity.class);
//                intent.putExtra("contentId", poiItemHashMap.get(String.valueOf(position)).getPoiContentId());
//                intent.putExtra("contentTypeId", poiItemHashMap.get(String.valueOf(position)).getPoiContentTypeId());
//                intent.putExtra("api","tour");
//                startActivity(intent);
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//
//            }
//        }));
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
        /*
        Log.d("myTag", "Course: " + courseId);
        db.collection("travel").document(courseId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        TravelItem travelItem = documentSnapshot.toObject(TravelItem.class);
                        Log.d("myTag", "Course: " + travelItem.getScheduleItems().get(String.valueOf(pos)).getScheduleDayIdx());
                        scheduleItem = travelItem.getScheduleItems().get(String.valueOf(pos));

                        TextView courseDayIdx = (TextView) findViewById(R.id.course_day_idx);
                        TextView courseDayDate = (TextView) findViewById(R.id.course_day_date);
                        TextView courseStAdd = (TextView) findViewById(R.id.course_start_add);
                        TextView courseDstAdd = (TextView) findViewById(R.id.course_dst_add);
                        courseDayIdx.setText(scheduleItem.getScheduleDayIdx());
                        courseDayDate.setText(scheduleItem.getScheduleDate());
                        courseDstAdd.setText(scheduleItem.getScheduleDst());
                        courseStAdd.setText(scheduleItem.getScheduleSt());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("myTag", "Course: Error getting document", e);
                    }
                });
         */
        Log.i("myTag","Course: "+pos);
        db.collection("course").document(courseId+"_"+pos)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Log.i("myTag", "Course: "+courseId);
                        courseItem = (CourseItem) documentSnapshot.toObject(CourseItem.class);
                        courseCount = courseItem.getPoiItemHashMap().size();
                        Log.d("myTag", "Course: " + courseItem.getCourseItemId());

                        TextView courseDayIdx = (TextView) findViewById(R.id.course_day_idx);
                        TextView courseDayDate = (TextView) findViewById(R.id.course_day_date);
                        TextView courseStAdd = (TextView) findViewById(R.id.course_start_add);
                        TextView courseDstAdd = (TextView) findViewById(R.id.course_dst_add);
                        courseDayIdx.setText(courseItem.getCourseDayIdx());
                        courseDayDate.setText(courseItem.getCourseDate());
                        courseDstAdd.setText(courseItem.getCourseDstAdd());
                        courseStAdd.setText(courseItem.getCourseStAdd());

                        courseCount = courseItem.getPoiItemHashMap().size();
                        for(int i = 0; i<courseCount; i++){
                            poiItemHashMap.put(String.valueOf(i),courseItem.getPoiItemHashMap().get(String.valueOf(i)));
                        }
                        courseAdapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("myTag", "Course: Error getting document", e);
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100){
            Log.i("myTag", "Course: PoiItem received!");
            if(resultCode == 100){
                PoiItem poiItem = (PoiItem) data.getSerializableExtra("poiItem");
                Log.i("myTag", "Course: "+poiItem.getPoiTitle());
                poiItem.setCourseItemId(courseId+"_"+pos);
                poiItemHashMap.put(String.valueOf(courseCount++),poiItem);
                courseItem.setPoiItemHashMap(poiItemHashMap);
                db.collection("course").document(courseId+"_"+pos)
                        .update("poiItemHashMap",poiItemHashMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("myTag", "Course: DocumentSnapshot successfully updated!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("myTag", "Course: Error updating document", e);
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

    public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{
        private GestureDetector gestureDetector;
        private CourseActivity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final CourseActivity.ClickListener clickListener) {
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
        public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
