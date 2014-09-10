package com.leonardofrangelli.floriparoutes.adapters;

import android.content.Context;
import android.widget.SimpleAdapter;

import com.leonardofrangelli.floriparoutes.R;

import java.util.List;
import java.util.Map;

/**
 * Created by frangelli on 9/9/14.
 */
public class StopsAdapter extends SimpleAdapter {

    public StopsAdapter(Context context, List<Map<String,String>> values) {
        super(context,values, R.layout.stop_row_layout,new String[]{"name","sequence"},new int[]{R.id.nameStopTextView,R.id.sequenceStopTextView});
    }

}
