package com.myseoultravel.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.myseoultravel.fragment.CultureFragment;
import com.myseoultravel.fragment.ExperienceFragment;
import com.myseoultravel.fragment.FoodFragment;
import com.myseoultravel.fragment.ShoppingFragment;

public class SearchNearbyPlaceAdapter extends androidx.fragment.app.FragmentPagerAdapter {

    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[] {"SHOPPING", "FOOD", "CULTURE", "EXPERIENCE"};

    int mNumOfTabs;
    public SearchNearbyPlaceAdapter(@NonNull FragmentManager fm) {
        super(fm);
        this.mNumOfTabs = PAGE_COUNT;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                ShoppingFragment shoppingFragment = new ShoppingFragment();
                return shoppingFragment;
            case 1:
                FoodFragment foodFragment = new FoodFragment();
                return foodFragment;
            case 2:
                CultureFragment cultureFragment = new CultureFragment();
                return cultureFragment;
            case 3:
                ExperienceFragment experienceFragment = new ExperienceFragment();
                return experienceFragment;
            default:
                return null;
        }
        //return null;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return tabTitles[position];
    }
}
