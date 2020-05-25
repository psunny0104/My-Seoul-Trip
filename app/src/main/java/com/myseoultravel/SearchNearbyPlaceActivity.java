package com.myseoultravel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.myseoultravel.adapter.SearchNearbyPlaceItemAdapter;
import com.myseoultravel.adapter.SearchNearbyPlaceTabAdapter;

public class SearchNearbyPlaceActivity extends AppCompatActivity {

    private static final String LOG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_nearby_place);

        //TabLayout
        TabLayout tabs = (TabLayout) findViewById(R.id.tabLayout);
        tabs.setTabGravity(tabs.GRAVITY_FILL);

        //어댑터 설정
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        final SearchNearbyPlaceTabAdapter searchNearbyPlaceTabAdapter = new SearchNearbyPlaceTabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(searchNearbyPlaceTabAdapter);

        //
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
    }
}
