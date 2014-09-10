package com.leonardofrangelli.floriparoutes.activities;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ListAdapter;

import com.leonardofrangelli.floriparoutes.adapters.DeparturesAdapter;
import com.leonardofrangelli.floriparoutes.adapters.StopsAdapter;
import com.leonardofrangelli.floriparoutes.common.DialogBuilder;
import com.leonardofrangelli.floriparoutes.data.dto.RoutesResponseDTO;
import com.leonardofrangelli.floriparoutes.data.entities.Departure;
import com.leonardofrangelli.floriparoutes.data.entities.Stop;
import com.leonardofrangelli.floriparoutes.tasks.GetDeparturesByRouteTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by frangelli on 9/9/14.
 */
public class DeparturesListFragment extends ListFragment {

    private RoutesResponseDTO dto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        this.dto = (RoutesResponseDTO) args.get("dto");
        DialogBuilder.showProgressDialog("Wait a moment..\nWe're searching your departures for " + this.getDto().getSelectedRoute().get("longName"),"Searching...",this.getActivity());
        new GetDeparturesByRouteTask().execute(this);
    }



    private void showDepartures(List<Departure> departures) {
        if(departures != null && !departures.isEmpty()) {
            List<Map<String,String>> dataList = parseDeparturesToMap(departures);
            ListAdapter adapter = new DeparturesAdapter(this.getActivity(),dataList);
            setListAdapter(adapter);
        } else {
            setListAdapter(null);
        }
    }

    private List<Map<String, String>> parseDeparturesToMap(List<Departure> departures) {
        List<Map<String,String>> dataList = new ArrayList<Map<String, String>>();

        for (Departure departure : departures) {
            Map<String,String> departureMap = new HashMap<String, String>();
            departureMap.put("id",String.valueOf(departure.getId()));
            departureMap.put("calendar",departure.getCalendar());
            departureMap.put("time",departure.getTime());
            dataList.add(departureMap);
        }

        return dataList;
    }

    public RoutesResponseDTO getDto() {
        return dto;
    }

    public void onReceiveListOfDepartures(List<Departure> result) {

        showDepartures(result);
        DialogBuilder.closeProgressDialog();
    }
}
