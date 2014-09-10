package com.leonardofrangelli.floriparoutes.adapters;

import android.content.Context;
import android.widget.SimpleAdapter;

import com.leonardofrangelli.floriparoutes.R;

import java.util.List;
import java.util.Map;

/**
 * Created by frangelli on 9/9/14.
 */
public class DeparturesAdapter extends SimpleAdapter {

    public DeparturesAdapter(Context context, List<Map<String,String>> values) {
        super(context,values, R.layout.departure_row_layout,new String[]{"calendar","time"},new int[]{R.id.calendarTextView,R.id.timeTextView});
    }
}
