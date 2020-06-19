package com.myseoultrip.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.myseoultrip.fragment.CultureFragment;
import com.myseoultrip.fragment.ExperienceFragment;
import com.myseoultrip.fragment.FoodFragment;
import com.myseoultrip.fragment.ShoppingFragment;
import com.myseoultrip.fragment.ToursiteFragment;

public class SearchNearbyTabAdapter extends androidx.fragment.app.FragmentPagerAdapter {

    final int PAGE_COUNT = 5;
    private String tabTitles[] = new String[] {"STORE", "FOOD","HIS/NAT", "CULTURE", "EXP"};

    int mNumOfTabs;
    public SearchNearbyTabAdapter(@NonNull FragmentManager fm) {
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
                ToursiteFragment toursiteFragment = new ToursiteFragment();
                return toursiteFragment;
            case 3:
                CultureFragment cultureFragment = new CultureFragment();
                return cultureFragment;
            case 4:
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
