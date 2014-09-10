package com.leonardofrangelli.floriparoutes.adapters;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.leonardofrangelli.floriparoutes.activities.DeparturesListFragment;
import com.leonardofrangelli.floriparoutes.activities.RouteDetailsActivity;
import com.leonardofrangelli.floriparoutes.activities.StopsListFragment;

/**
 * Created by frangelli on 9/9/14.
 */
public class RouteDetailsPagerAdapter extends FragmentPagerAdapter {

    private RouteDetailsActivity activity;

    public RouteDetailsPagerAdapter(FragmentManager fm, RouteDetailsActivity activity) {
        super(fm);
        this.activity = activity;
    }

    @Override
    public Fragment getItem(int i) {
        Bundle args = new Bundle();
        args.putSerializable("dto",this.activity.getDto());

        switch (i) {
            case 0:

                StopsListFragment stopsListFragment = new StopsListFragment();
                stopsListFragment.setArguments(args);
                return stopsListFragment;

            default:
                DeparturesListFragment departuresListFragment = new DeparturesListFragment();
                departuresListFragment.setArguments(args);
                return departuresListFragment;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Stops";

            default:
                return "Departures";
        }
    }
}
