package com.example.dell.babycare.Activities.developmentActivities.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.dell.babycare.Activities.developmentActivities.frags.FragmentFive;
import com.example.dell.babycare.Activities.developmentActivities.frags.FragmentFour;
import com.example.dell.babycare.Activities.developmentActivities.frags.FragmentOne;
import com.example.dell.babycare.Activities.developmentActivities.frags.FragmentSeven;
import com.example.dell.babycare.Activities.developmentActivities.frags.FragmentSix;
import com.example.dell.babycare.Activities.developmentActivities.frags.FragmentThree;
import com.example.dell.babycare.Activities.developmentActivities.frags.FragmentTwo;


/**
 * Created by DELL on 1/3/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new FragmentOne();
                break;
            case 1:
                fragment = new FragmentTwo();
                break;
            case 2:
                fragment = new FragmentThree();
                break;
            case 3:
                fragment = new FragmentFour();
                break;
            case 4:
                fragment = new FragmentFive();
                break;
            case 5:
                fragment = new FragmentSix();
                break;
            case 6:
                fragment = new FragmentSeven();
                break;

        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "ေမြးကင္းစမွ၃လ";
                break;
            case 1:
                title = "၃လမွ၆လ";
                break;
            case 2:
                title = "၆လမွ၁၀လ";
                break;
            case 3:
                title = "၁၀လမွ၂ႏွွစ္";
                break;
            case 4:
                title = "၂ႏွစ္မွ၃ႏွစ္";
                break;
            case 5:
                title = "၃ႏွွစ္မွ၅ႏွစ္";
                break;
            case 6:
                title = "၅ႏွစ္မွ၆ႏွစ္";
                break;
        }
        return title;
    }
}
