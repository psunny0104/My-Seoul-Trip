package com.myseoultrip.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myseoultrip.R;
import com.myseoultrip.SearchDetailActivity;
import com.myseoultrip.adapter.SearchNearbyItem;
import com.myseoultrip.adapter.SearchNearbyItemAdapter;
import com.myseoultrip.model.place.tour.LocalListModelItems;
import com.myseoultrip.service.ApiCallback;
import com.myseoultrip.service.TourApiClient;
import com.myseoultrip.service.TourApiService;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ToursiteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToursiteFragment extends Fragment {
    public ToursiteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_toursite, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.search_nearby_place_tour_site_recycler_view);
        recyclerView.setHasFixedSize(true);

        ArrayList<SearchNearbyItem> searchNearbyItems = new ArrayList<>();
        TourApiClient tourApiClient;
        searchNearbyItems.clear();
        Context context = view.getContext();
        tourApiClient = TourApiClient.getInstance(context).createBaseApi();

        double mapX = getActivity().getIntent().getDoubleExtra("mapX",0.0);
        double mapY = getActivity().getIntent().getDoubleExtra("mapY",0.0);
        tourApiClient.getLocationList(TourApiService.SERVICE_KEY, TourApiService.MOBILE_OS, TourApiService.MOBILE_App, TourApiService.MY_TYPE, 30, 1, "P", 76, mapX, mapY, 4000, new ApiCallback<LocalListModelItems>() {
            @Override
            public void onError(Throwable t) {
                Log.e("myTag", t.toString());
            }

            @Override
            public void onSuccess(int code, LocalListModelItems receivedData) throws JSONException {
                LocalListModelItems localList = (LocalListModelItems) receivedData;

                int size;
                if(localList.getResponse().getBody().getTotalCount() < 30)
                    size = localList.getResponse().getBody().getTotalCount();
                else
                    size = 30;
                Log.i("myTag", "개수: "+size);

                for(int i = 0; i<size; i++){
                    String engName = localList.getResponse().getBody().getItems().getItem().get(i).getTitle();
                    String photo = localList.getResponse().getBody().getItems().getItem().get(i).getFirstimage();
                    searchNearbyItems.add(new SearchNearbyItem(engName,photo));
                    Log.i("myTag", i+"번째 이름: "+engName);
                }
                SearchNearbyItemAdapter adapter = new SearchNearbyItemAdapter(context, searchNearbyItems);
                recyclerView.setAdapter(adapter);

                adapter.setOnItemClickListener(new SearchNearbyItemAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        // TODO : 아이템 클릭 이벤트를 MainActivity에서 처리.
                        Intent oldIntent = getActivity().getIntent();
                        Intent intent = new Intent(getContext(), SearchDetailActivity.class);
                        intent.putExtra("contentId", localList.getResponse().getBody().getItems().getItem().get(position).getContentid());
                        intent.putExtra("contentTypeId", localList.getResponse().getBody().getItems().getItem().get(position).getContenttypeid());
                        intent.putExtra("api","tour");
                        intent.putExtra("pos",oldIntent.getIntExtra("pos",0));
                        intent.putExtra("courseId",oldIntent.getStringExtra("courseId"));
                        intent.putExtra("poiCount",oldIntent.getIntExtra("poiCount",0));
                        Log.i("myTag", localList.getResponse().getBody().getItems().getItem().get(position).toContetIdString());
                        intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
                        startActivity(intent);
                        getActivity().finish();                    }
                }) ;
            }

            @Override
            public void onFailure(int code) {
                Log.e("myTag", "Failure"+code);
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        Log.i("myTag","debug2: "+ searchNearbyItems.size());

        return view;
    }
}
