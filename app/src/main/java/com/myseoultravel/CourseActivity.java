package com.myseoultravel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
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
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.myseoultravel.adapter.CourseAdapter;
import com.myseoultravel.adapter.CourseItem;
import com.myseoultravel.adapter.PoiItem;
import com.myseoultravel.adapter.ScheduleItem;
import com.myseoultravel.model.place.tour.CategoryCodeModel;

import java.util.ArrayList;

public class CourseActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    String courseId = "";
    int pos;
    ScheduleItem scheduleItem;
    CourseItem courseItem;
    int poiCount = 0;
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
        selectAreaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(getApplicationContext(),SelectAreaActivity.class);
                newIntent.putExtra("courseId",courseId);
                newIntent.putExtra("pos",pos);
                newIntent.putExtra("poiCount",poiCount);
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100){
            Log.i("myTag", "Course: PoiItem received!");
            if(resultCode == 100){
                PoiItem poiItem = (PoiItem) data.getSerializableExtra("poiItem");
                Log.i("myTag", "Course: "+poiItem.getPoiTitle());
                poiItem.setTravelItemId(courseId);
                poiItem.setCourseItemId(courseId+"_"+pos);
                poiItems.add(poiItem);
                poiCount++;
                db.collection("poi").document(courseId+"_"+pos+"_"+data.getIntExtra("poiCount",0))
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
