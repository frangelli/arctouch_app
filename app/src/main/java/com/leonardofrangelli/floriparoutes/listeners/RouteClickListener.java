package com.leonardofrangelli.floriparoutes.listeners;

import android.view.View;
import android.widget.AdapterView;

import com.leonardofrangelli.floriparoutes.activities.MainActivity;
import com.leonardofrangelli.floriparoutes.common.DialogBuilder;
import com.leonardofrangelli.floriparoutes.tasks.GetStopsByRouteTask;

import java.util.Map;

/**
 * Created by frangelli on 9/8/14.
 */
public class RouteClickListener implements AdapterView.OnItemClickListener {

    private MainActivity activity;

    public RouteClickListener(){}
    public RouteClickListener(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Map<String, String> item = (Map<String, String>) this.activity.getRoutesListView().getItemAtPosition(i);
        this.activity.setSelectedRouteId(Integer.valueOf(item.get("id")));
        this.activity.setSelectedRouteMap(item);
        DialogBuilder.showProgressDialog("Wait a moment..\nWe're searching the Stops for " + this.activity.getSelectedRouteMap().get("longName"), "Searching...", this.activity);
        new GetStopsByRouteTask().execute(this.activity);
    }
}
