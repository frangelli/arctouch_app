package com.leonardofrangelli.floriparoutes.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.leonardofrangelli.floriparoutes.R;
import com.leonardofrangelli.floriparoutes.tasks.GetStreetNameTask;

/**
 * Created by frangelli on 9/9/14.
 */
public class MapActivity extends Activity {

    private GoogleMap map;
    private String latLong;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Boolean firstTime = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        configureMap();

        Toast.makeText(this,"Longclick in a street to search", Toast.LENGTH_LONG).show();

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latLong = location.getLatitude() + "," + location.getLongitude();
                if(firstTime) {
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(),location.getLongitude()),16));
                    firstTime = false;
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);


    }

    private void configureMap() {

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        map.setMyLocationEnabled(true);

        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                executeGetStreetNameTask(latLng);
            }
        });
    }

    public void executeGetStreetNameTask(LatLng latLng) {
        this.latLong = latLng.latitude + "," + latLng.longitude;
        new GetStreetNameTask().execute(this);
    }

    public void onStreetNameReceived(String streetName) {
        if (!streetName.isEmpty()) {
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("streetName",streetName);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(this,"Ops! Was impossible to get your selected street!",Toast.LENGTH_SHORT).show();
        }
    }

    public GoogleMap getMap() {
        return map;
    }

    public String getLatLong() {
        return latLong;
    }

    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, locationListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(locationListener);
    }
}
