package com.leonardofrangelli.floriparoutes.tasks;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import com.leonardofrangelli.floriparoutes.activities.MainActivity;
import com.leonardofrangelli.floriparoutes.activities.RouteDetailsActivity;
import com.leonardofrangelli.floriparoutes.common.DialogBuilder;
import com.leonardofrangelli.floriparoutes.data.dto.RoutesResponseDTO;
import com.leonardofrangelli.floriparoutes.data.entities.Stop;
import com.leonardofrangelli.floriparoutes.data.services.RoutesService;

import java.util.List;

/**
 * Created by frangelli on 9/8/14.
 */
public class GetStopsByRouteTask extends AsyncTask<MainActivity, Void, List<Stop>> {

    private MainActivity activity;

    @Override
    protected List<Stop> doInBackground(MainActivity... activities) {
        this.activity = activities[0];

        return RoutesService.getInstance().findStopsByRouteId(this.activity.getSelectedRouteId());
    }

    @Override
    protected void onPostExecute(List<Stop> result) {
        RoutesResponseDTO dto = new RoutesResponseDTO();
        dto.setStops(result);
        dto.setSelectedRoute(this.activity.getSelectedRouteMap());

        Intent intent = new Intent(this.activity, RouteDetailsActivity.class);
        intent.putExtra("details",dto);
        activity.startActivity(intent);

    }
}
