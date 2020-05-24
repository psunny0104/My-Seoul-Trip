package com.myseoultravel.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myseoultravel.R;
import com.myseoultravel.adapter.SearchNearbyPlaceItem;
import com.myseoultravel.adapter.SearchNearbyPlaceItemAdapter;
import com.myseoultravel.model.place.nearby.NearbyPlaceItem;
import com.myseoultravel.service.GoogleCallback;
import com.myseoultravel.service.GooglePlaceClient;
import com.myseoultravel.service.GooglePlaceService;

import java.util.ArrayList;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link ShoppingFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class ShoppingFragment extends Fragment {
    public ShoppingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.search_nearby_place_shopping_recycler_view);
        recyclerView.setHasFixedSize(true);

        ArrayList<SearchNearbyPlaceItem> searchNearbyPlaceItems = new ArrayList<>();
        GooglePlaceClient googlePlaceClient;
        searchNearbyPlaceItems.clear();
        Context context = view.getContext();
        googlePlaceClient = GooglePlaceClient.getInstance(context).createBaseApi();

        googlePlaceClient.getNearbyPlace(GooglePlaceService.KEY, "store", GooglePlaceService.RADIUS, "37.560892, 126.986168",GooglePlaceService.LANGUAGE, new GoogleCallback() {
            @Override
            public void onError(Throwable t) {
                Log.e("myTag", t.toString());
            }

            @Override
            public void onSuccess(int code, Object receivedData) {
                NearbyPlaceItem nearbyPlaceItem = (NearbyPlaceItem) receivedData;

                Log.i("myTag", "개수: "+nearbyPlaceItem.getResults().size());
                for(int i = 0; i<nearbyPlaceItem.getResults().size(); i++){
                    String engName = nearbyPlaceItem.getResults().get(i).getName();
                    String PHOTOREF = "";
                    try{
                        PHOTOREF = nearbyPlaceItem.getResults().get(i).getPhotos().get(0).getPhotoReference().toString();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    String photo = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="+PHOTOREF+"&key="+GooglePlaceService.KEY;
                    searchNearbyPlaceItems.add(new SearchNearbyPlaceItem(engName,photo));
                    Log.i("myTag", i+"번째 이름: "+engName);
                }
                SearchNearbyPlaceItemAdapter adapter = new SearchNearbyPlaceItemAdapter(context, searchNearbyPlaceItems);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(int code) {
                Log.e("myTag", "Failure"+code);
            }
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        Log.i("myTag","debug2: "+searchNearbyPlaceItems.size());

        return view;
    }

    /*사용할 데이터를 미리 준비해 놓는다. 준비하는 형태에 따라 구현방법이 조금 달라 질 수 있다.
    1. 수동으로 Item을 입력해서 추가 하도록 할 수 있다.
    2. 온라인에서 DB에서 일괄 가져 올 수 도 있다.
    어떻게든 itmes 배열에 데이터를 형식에 맞게 넣어 어답터와 연결만 하면 화면에 내용이 출력될것이다.
    */
}
