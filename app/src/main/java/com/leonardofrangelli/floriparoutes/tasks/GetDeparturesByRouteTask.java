package com.leonardofrangelli.floriparoutes.tasks;

import android.content.Intent;
import android.os.AsyncTask;

import com.leonardofrangelli.floriparoutes.activities.DeparturesListFragment;
import com.leonardofrangelli.floriparoutes.activities.RouteDetailsActivity;
import com.leonardofrangelli.floriparoutes.data.dto.RoutesResponseDTO;
import com.leonardofrangelli.floriparoutes.data.entities.Departure;
import com.leonardofrangelli.floriparoutes.data.services.RoutesService;

import java.util.List;

/**
 * Created by frangelli on 9/9/14.
 */
public class GetDeparturesByRouteTask extends AsyncTask<DeparturesListFragment, Void, List<Departure>> {

    private DeparturesListFragment fragment;
    private RouteDetailsActivity activity;
    @Override
    protected List<Departure> doInBackground(DeparturesListFragment... fragments) {
        this.fragment = fragments[0];
        this.activity = (RouteDetailsActivity) this.fragment.getActivity();
        return RoutesService.getInstance().findDeparturesByRouteId(this.activity.getSelectedRouteId());
    }

    @Override
    protected void onPostExecute(List<Departure> result) {

        RoutesResponseDTO dto = this.activity.getDto();
        dto.setDepartures(result);
        this.fragment.onReceiveListOfDepartures(result);

    }
}
