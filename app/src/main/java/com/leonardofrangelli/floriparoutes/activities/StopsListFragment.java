package com.leonardofrangelli.floriparoutes.activities;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.leonardofrangelli.floriparoutes.adapters.StopsAdapter;
import com.leonardofrangelli.floriparoutes.data.dto.RoutesResponseDTO;
import com.leonardofrangelli.floriparoutes.data.entities.Stop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by frangelli on 9/9/14.
 */
public class StopsListFragment extends ListFragment {

    private ListView stopsListView;
    private RoutesResponseDTO dto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        this.dto = (RoutesResponseDTO) args.get("dto");
        showStops(this.dto.getStops());
    }

    private void showStops(List<Stop> stops) {
        if(stops != null && !stops.isEmpty()) {
            List<Map<String,String>> dataList = parseStopsToMap(stops);
            ListAdapter adapter = new StopsAdapter(this.getActivity(),dataList);
            setListAdapter(adapter);
        } else {
            setListAdapter(null);
        }
    }

    private List<Map<String, String>> parseStopsToMap(List<Stop> stops) {
        List<Map<String,String>> dataList = new ArrayList<Map<String, String>>();

        for (Stop stop : stops) {
            Map<String,String> stopMap = new HashMap<String, String>();
            stopMap.put("id",String.valueOf(stop.getId()));
            stopMap.put("name",stop.getName());
            stopMap.put("sequence",String.valueOf(stop.getSequence()));
            dataList.add(stopMap);
        }

        return dataList;
    }
}
