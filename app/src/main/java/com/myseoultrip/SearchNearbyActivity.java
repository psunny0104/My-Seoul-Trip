package com.myseoultrip;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.myseoultrip.adapter.SearchNearbyTabAdapter;

public class SearchNearbyActivity extends AppCompatActivity {

    private static final String LOG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_nearby_place);
        setToolbar();

        //TabLayout
        TabLayout tabs = (TabLayout) findViewById(R.id.tabLayout);
        tabs.setTabGravity(tabs.GRAVITY_FILL);

        //어댑터 설정
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        final SearchNearbyTabAdapter searchNearbyTabAdapter = new SearchNearbyTabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(searchNearbyTabAdapter);

        //
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
    }

    private void setToolbar() {
        Toolbar scheduleBar = (Toolbar) findViewById(R.id.search_nearby_toolbar);
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
}
