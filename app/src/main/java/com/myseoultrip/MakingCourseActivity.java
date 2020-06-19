package com.myseoultrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.myseoultrip.adapter.CourseItem;
import com.myseoultrip.adapter.MakingAdapter;
import com.myseoultrip.adapter.MakingItem;
import com.myseoultrip.adapter.PoiItem;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import static com.myseoultrip.SplashActivity.subwayCoords;
import static com.myseoultrip.service.findroute.DijkStraCourse.initDijkTwo;
import static com.myseoultrip.service.findroute.FirstStepPermutation.permList;
import static com.myseoultrip.service.findroute.FirstStepPermutation.permutation;
import static com.myseoultrip.service.findroute.SecondStepDijkstra.codeToIdx;
import static com.myseoultrip.service.findroute.SecondStepDijkstra.initDijk;
import static com.myseoultrip.service.findroute.SecondStepDijkstra.subwayIdx;

public class MakingCourseActivity extends AppCompatActivity {

    public static ArrayList<MakingItem> makingItems = new ArrayList<>();
    CourseItem courseItem;
    public ArrayList<PoiItem> poiItems = new ArrayList<>();
    public ArrayList<PoiItem> orderedPoiItems = new ArrayList<>();
    public ArrayList<Double> poiDists = new ArrayList<>();
    public ArrayList<Integer> poiSubwayIdxs = new ArrayList<>();
    String courseId;
    String travelId;
    int pos;
    int poiCount = 0;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    PoiItem startPoi = new PoiItem();
    PoiItem endPoi = new PoiItem();

    private FirebaseAuth mAuth;
    MakingAdapter makingAdapter;
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_making_course);
        setToolbar();

        for(int i = 0; i<subwayCoords.size(); i++){
            //Log.i("myTag", subwayCoords.get(i).getStationName());
        }

        Intent intent = getIntent();
        pos = intent.getIntExtra("pos",0);
        courseId = intent.getStringExtra("courseId");
        travelId = intent.getStringExtra("travelId");
        getMakingDb();


        mRecyclerView = (RecyclerView) findViewById(R.id.making_recycler);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        orderedPoiItems = new ArrayList<>();
        makingItems = new ArrayList<>();
        makingAdapter = new MakingAdapter(makingItems);
        makingAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(makingAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

    }

    public void getMakingDb() {
        if(!poiItems.isEmpty())
            poiItems.clear();
        if(!poiSubwayIdxs.isEmpty())
            poiSubwayIdxs.clear();
        if(!poiDists.isEmpty())
            poiDists.clear();
        db.collection("course").document(courseId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Log.d("myTag", "Making: "+courseId);
                        courseItem = (CourseItem) documentSnapshot.toObject(CourseItem.class);

                        db.collection("poi").whereEqualTo("courseItemId",courseId)
                                .get()
                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                    @Override
                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                        poiCount = queryDocumentSnapshots.size();
                                        if(poiCount != 0){
                                            for(int i = 0; i<poiCount; i++){
                                                PoiItem poiItem = queryDocumentSnapshots.getDocuments().get(i).toObject(PoiItem.class);
                                                poiItems.add(poiItem);
                                                Log.d("myTag", "Making: " + queryDocumentSnapshots.getDocuments().get(i).getData());
                                            }
                                        }
                                        //adapter.notifyDataSetChanged();
                                        calDist();
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
                        Log.w("myTag", "Making: Error getting document", e);
                    }
                });
    }

    private void calDist() {
        Double startDist = 987654321.0;
        Double endDist = 987654321.0;
        int startIdx = 0;
        int endIdx = 0;

        //출발지, 도착지 Lat = Y 30~, Lng = X
        startDist = distance(courseItem.getCourseStLat(),courseItem.getCourseStLng(),subwayCoords.get(0).getWgsY(),subwayCoords.get(0).getWgsX(),"kilometer");
        //Log.i("myTag",courseItem.getCourseStLat()+" "+courseItem.getCourseStLng());
        //Log.i("myTag",subwayCoords.get(0).getWgsY()+" "+subwayCoords.get(0).getWgsX());


        for(int j = 0; j<subwayCoords.size(); j++){
            //Log.i("myTag", "Making: "+subwayCoords.get(j).getWgsY());
            Double tmpStart = distance(courseItem.getCourseStLat(),courseItem.getCourseStLng(),subwayCoords.get(j).getWgsY(),subwayCoords.get(j).getWgsX(),"kilometer");
            Double tmpEnd = distance(courseItem.getCourseDstLat(),courseItem.getCourseDstLng(),subwayCoords.get(j).getWgsY(),subwayCoords.get(j).getWgsX(),"kilometer");

            //Log.i("myTag","Making: calDist = "+tmpStart+" idx: "+j);

            if(startDist>=tmpStart){
                startDist = tmpStart;
                startIdx = Integer.parseInt(subwayCoords.get(j).getStationCd());
            }
            if(endDist>=tmpEnd){
                endDist = tmpEnd;
                endIdx = Integer.parseInt(subwayCoords.get(j).getStationCd());;
            }
        }


        Log.i("myTag","Making: startDist = "+startDist+" idx: "+startIdx);
        Log.i("myTag","Making: endDist = "+endDist+" idx: "+endIdx);

        //poi
        for(int j =0; j<poiItems.size(); j++){
            Double poiDist = 987654321.0;
            Integer poiIdx = 0;
            for(int i = 0; i<subwayCoords.size(); i++){
                Double tmpPoi = distance(poiItems.get(j).getPoiMapY(),poiItems.get(j).getPoiMapX(),subwayCoords.get(i).getWgsY(),subwayCoords.get(i).getWgsX(),"kilometer");
                if(poiDist >= tmpPoi){
                    poiDist = tmpPoi;
                    poiIdx = Integer.parseInt(subwayCoords.get(i).getStationCd());
                }
            }
            poiDists.add(poiDist);
            poiSubwayIdxs.add(poiIdx);
        }
        for(int i = 0; i<poiDists.size(); i++){
            Log.i("myTag","Making: poi"+i+": "+poiDists.get(i));
            Log.i("myTag","Making: poi idx"+i+": "+ poiSubwayIdxs.get(i));
            poiItems.get(i).setLineName(subwayCoords.get(poiSubwayIdxs.get(i)).getLineNum());
            poiItems.get(i).setStationName(subwayCoords.get(poiSubwayIdxs.get(i)).getStationName());
            poiItems.get(i).setStationNameEng(subwayCoords.get(poiSubwayIdxs.get(i)).getEngName());
            poiItems.get(i).setSubwayIdx(poiSubwayIdxs.get(i));
            poiItems.get(i).setDist(poiDists.get(i));
        }

        startPoi.setLineName(subwayCoords.get(startIdx).getLineNum());
        startPoi.setStationName(subwayCoords.get(startIdx).getStationName());
        startPoi.setStationNameEng(subwayCoords.get(startIdx).getEngName());
        startPoi.setSubwayIdx(startIdx);
        startPoi.setDist(startDist);

        endPoi.setLineName(subwayCoords.get(endIdx).getLineNum());
        endPoi.setStationName(subwayCoords.get(endIdx).getStationName());
        endPoi.setStationNameEng(subwayCoords.get(endIdx).getEngName());
        endPoi.setSubwayIdx(endIdx);
        endPoi.setDist(endDist);

        //경로 구하기
        int[] arrStation = new int[poiSubwayIdxs.size()+2];

        arrStation[0] = startIdx;
        for(int i = 0; i< poiSubwayIdxs.size(); i++){
            arrStation[i+1] = poiSubwayIdxs.get(i);
        };
        arrStation[poiSubwayIdxs.size()+1] = endIdx;

        int sizeString = arrStation.length;
        boolean[] visited = new boolean[sizeString];

        if(!permList.isEmpty())
            permList.clear();

        //firstStep
        permutation(arrStation, 0, sizeString, sizeString);

        //secondStep
        //ArrayList<Integer> permTime = new ArrayList<Integer>();
        int itemTime = 0;
        int minIdx = 987654321;
        int minTime = 987654321;
        //thirdStep: 최소 시간 찾기, 중복되는 순열에 대한 가지치기 필요
        for (int i = 0; i < permList.size(); i++) {
            //System.out.println(permList.get(i).size());
            //Log.i("myTag","Making: "+"idx"+codeToIdx.get(permList.get(i).get(0)));
            //Log.i("myTag","Making: "+"idx"+codeToIdx.get(permList.get(i).get(permList.get(i).size() - 1)));

            if(!(
                    (permList.get(i).get(0).equals(startIdx)) &&
                            (permList.get(i).get(permList.get(i).size() - 1).equals(endIdx))
            )){
                continue;
            }

            for (int j = 0; j < permList.get(i).size() - 1; j++) {

                int tmp = 0;
                try {
                    tmp = initDijkTwo(permList.get(i).get(j), permList.get(i).get(j + 1), 0, 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //Log.i("myTag",i + 1 + "번째 순열의 " + j + 1 + "번째 구간의 코스트: " + tmp);
                itemTime += tmp;
            }
            //Log.i("myTag",i + 1 + "번째 순열의 총 cost: " + itemTime);
            if(itemTime<=minTime){
                minTime = itemTime;
                minIdx = i;
            }
            //permTime.add(itemTime);
            itemTime = 0;
        }

        //int min = Collections.min(permTime);
        Log.i("myTag","최소 시간: " + minTime);
        Log.i("myTag", "최소 시간 idx: "+(minIdx+1));

        startPoi.setOrder(0);
        orderedPoiItems.add(startPoi);
        for(int i = 0; i<permList.get(minIdx).size(); i++){
            for(int j = 0; j<poiItems.size(); j++){
                if(subwayCoords.get(permList.get(minIdx).get(i).intValue()).getStationName().equals(poiItems.get(j).getStationName())){
                    poiItems.get(j).setOrder(i+1);
                    Log.i("myTag",i+1+"번째 역: " +subwayCoords.get(permList.get(minIdx).get(i).intValue()).getEngName());
                    orderedPoiItems.add(poiItems.get(j));
                }
            }
//            MakingItem makingItem = new MakingItem(subwayCoords.get(codeToIdx.get(permList.get(minIdx).get(i).intValue())).getEngName());
        }
        endPoi.setOrder(permList.get(minIdx).size()+1);
        orderedPoiItems.add(endPoi);
        //makingAdapter.notifyDataSetChanged();


        //MakingItem 만들어서 넣기
        //start->첫번쨰지하철역
        MakingItem makingItem = new MakingItem("Starting pt",
                1,
                subwayCoords.get(startIdx).getLineNum(),
                subwayCoords.get(startIdx).getEngName()+" / "+subwayCoords.get(startIdx).getStationName(),
                orderedPoiItems.get(1).getPoiTitle(),2,
                subwayCoords.get(permList.get(minIdx).get(1).intValue()).getLineNum(),
                subwayCoords.get(permList.get(minIdx).get(1).intValue()).getEngName()+" / "+subwayCoords.get(permList.get(minIdx).get(1).intValue()).getStationName()
        );
        makingItems.add(makingItem);
        for(int i = 1; i<orderedPoiItems.size()-2; i++){
            makingItem = new MakingItem(orderedPoiItems.get(i).getPoiTitle(),
                    i+1,
                    subwayCoords.get(permList.get(minIdx).get(i).intValue()).getLineNum(),
                    subwayCoords.get(permList.get(minIdx).get(i).intValue()).getEngName()+" / "+subwayCoords.get(permList.get(minIdx).get(i).intValue()).getStationName(),
                    orderedPoiItems.get(i+1).getPoiTitle(),i+2,
                    subwayCoords.get(permList.get(minIdx).get(i+1).intValue()).getLineNum(),
                    subwayCoords.get(permList.get(minIdx).get(i).intValue()).getEngName()+" / "+subwayCoords.get(permList.get(minIdx).get(i+1).intValue()).getStationName()
            );
            makingItems.add(makingItem);
        };
        makingItem = new MakingItem(orderedPoiItems.get(orderedPoiItems.size()-2).getPoiTitle(),
                orderedPoiItems.size()-1,
                subwayCoords.get(permList.get(minIdx).get(permList.get(minIdx).size()-2).intValue()).getLineNum(),
                subwayCoords.get(permList.get(minIdx).get(orderedPoiItems.size()-2).intValue()).getEngName()+" / "+subwayCoords.get(permList.get(minIdx).get(orderedPoiItems.size()-2).intValue()).getStationName(),
                "Last dst",orderedPoiItems.size(),
                subwayCoords.get(endIdx).getLineNum(),
                subwayCoords.get(endIdx).getEngName()+" / "+subwayCoords.get(endIdx).getStationName()
        );
        makingItems.add(makingItem);
        makingAdapter.notifyDataSetChanged();
        //상세경로
        for (int j = 0; j < permList.get(minIdx).size() - 1; j++) {
            try {
                initDijkTwo(permList.get(minIdx).get(j), permList.get(minIdx).get(j + 1), 1, j);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        makingAdapter.notifyDataSetChanged();



    }

    public static Double distance(Double lat1, Double lon1, Double lat2, Double lon2, String unit) {

        Double theta = lon1 - lon2;
        Double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        if (unit == "kilometer") {
            dist = dist * 1.609344;
        } else if(unit == "meter"){
            dist = dist * 1609.344;
        }

        return (dist);
    }


    // This function converts decimal degrees to radians
    private static Double deg2rad(Double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    private static Double rad2deg(Double rad) {
        return (rad * 180 / Math.PI);
    }

    private void setToolbar() {
        Toolbar scheduleBar = (Toolbar) findViewById(R.id.making_toolbar);
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
}