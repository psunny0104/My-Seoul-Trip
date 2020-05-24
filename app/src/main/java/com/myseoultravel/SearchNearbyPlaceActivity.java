package com.myseoultravel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.myseoultravel.adapter.SearchNearbyPlaceAdapter;
import com.myseoultravel.fragment.FoodFragment;
import com.myseoultravel.service.GoogleCallback;
import com.myseoultravel.service.GooglePlaceClient;
import com.myseoultravel.service.GooglePlaceService;

public class SearchNearbyPlaceActivity extends AppCompatActivity {

    private static final String LOG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_nearby_place);

        //TabLayout
        TabLayout tabs = (TabLayout) findViewById(R.id.tabLayout);
        tabs.setTabGravity(tabs.GRAVITY_FILL);

        //어댑터 설정정
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        final SearchNearbyPlaceAdapter searchNearbyPlaceAdapter = new SearchNearbyPlaceAdapter(getSupportFragmentManager());
        viewPager.setAdapter(searchNearbyPlaceAdapter);

        //
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

        Intent intent = getIntent();
//        Log.i("myLog", intent.getStringExtra("name").toString());

    }
}
