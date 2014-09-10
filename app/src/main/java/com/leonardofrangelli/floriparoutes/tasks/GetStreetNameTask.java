package com.leonardofrangelli.floriparoutes.tasks;

import android.os.AsyncTask;

import com.leonardofrangelli.floriparoutes.activities.MapActivity;
import com.leonardofrangelli.floriparoutes.data.common.GeocodingHttpClient;

/**
 * Created by frangelli on 9/9/14.
 */
public class GetStreetNameTask extends AsyncTask<MapActivity, Void, String> {

    private MapActivity activity;

    @Override
    protected String doInBackground(MapActivity... activities) {
        this.activity = activities[0];
        return GeocodingHttpClient.getStreetName(this.activity.getLatLong());
    }

    @Override
    protected void onPostExecute(String streetName) {
        this.activity.onStreetNameReceived(streetName);
    }


}
