package com.example.dell.babycare.Activities.developmentActivities.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.dell.babycare.Activities.developmentActivities.frags.First_frg;
import com.example.dell.babycare.Activities.developmentActivities.frags.Five_frg;
import com.example.dell.babycare.Activities.developmentActivities.frags.Fouth_frg;
import com.example.dell.babycare.Activities.developmentActivities.frags.Second_frg;
import com.example.dell.babycare.Activities.developmentActivities.frags.Third_frg;

/**
 * Created by Admin on 1/14/2018.
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter{
    public MyPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:
                fragment=new First_frg();
                break;
            case 1:
                fragment=new Second_frg();
                break;
            case 2:
                fragment=new Third_frg();
                break;
            case 3:
                fragment=new Fouth_frg();
                break;
            case 4:
                fragment=new Five_frg();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
