package com.leonardofrangelli.floriparoutes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.leonardofrangelli.floriparoutes.R;

import java.util.List;
import java.util.Map;

/**
 * Created by frangelli on 9/6/14.
 */
public class RoutesAdapter extends SimpleAdapter {

    public RoutesAdapter(Context context, List<Map<String,String>> values) {
        super(context,values, R.layout.route_row_layout,new String[]{"longName","shortName"},new int[]{R.id.longNameTextView,R.id.shortNameTextView});
    }
}
