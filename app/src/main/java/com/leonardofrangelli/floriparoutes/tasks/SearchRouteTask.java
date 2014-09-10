package com.leonardofrangelli.floriparoutes.tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.leonardofrangelli.floriparoutes.activities.SearchableActivity;
import com.leonardofrangelli.floriparoutes.data.entities.Route;
import com.leonardofrangelli.floriparoutes.data.services.RoutesService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frangelli on 9/8/14.
 */
public class SearchRouteTask extends AsyncTask<SearchableActivity, Void, List<Route>> {

    private SearchableActivity activity;

    @Override
    protected List<Route> doInBackground(SearchableActivity... activities) {
        this.activity = activities[0];

        return RoutesService.getInstance().findRoutesByStopName(this.activity.getSearchValue());
    }

    @Override
    protected void onPostExecute(List<Route> routes) {
        this.activity.onReceiveListOfResults(routes);
    }


}
