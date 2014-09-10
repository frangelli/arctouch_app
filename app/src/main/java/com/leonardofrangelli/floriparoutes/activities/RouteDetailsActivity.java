package com.leonardofrangelli.floriparoutes.activities;

import android.app.ActionBar;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.widget.Toast;

import com.leonardofrangelli.floriparoutes.adapters.RouteDetailsPagerAdapter;
import com.leonardofrangelli.floriparoutes.common.DialogBuilder;
import com.leonardofrangelli.floriparoutes.data.dto.RoutesResponseDTO;
import com.leonardofrangelli.floriparoutes.R;
import com.leonardofrangelli.floriparoutes.data.entities.Departure;

import java.util.List;

/**
 * Created by frangelli on 9/6/14.
 */
public class RouteDetailsActivity extends FragmentActivity implements ActionBar.TabListener {

    private RoutesResponseDTO dto = null;
    private ViewPager viewPager;
    private RouteDetailsPagerAdapter routeDetailsPagerAdapter;
    private Integer selectedRouteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_details);
        DialogBuilder.closeProgressDialog();
        initializeViewComponents();

        //getting the content from MainActivity
        Intent activityMain = getIntent();
        this.dto = (RoutesResponseDTO) activityMain.getExtras().getSerializable("details");
        this.selectedRouteId = Integer.valueOf(dto.getSelectedRoute().get("id"));
        this.setTitle(dto.getSelectedRoute().get("longName"));

        //configuring the actionbar
        final ActionBar actionBar = getActionBar();
        if(actionBar != null) {

            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
            actionBar.setDisplayHomeAsUpEnabled(true);

            for (int i = 0; i < this.routeDetailsPagerAdapter.getCount(); i++) {
                actionBar.addTab(actionBar.newTab().setText(this.routeDetailsPagerAdapter.getPageTitle(i)).setTabListener(this));
            }
        }

    }

    private void initializeViewComponents() {
        this.viewPager = (ViewPager) findViewById(R.id.pager);
        this.routeDetailsPagerAdapter = new RouteDetailsPagerAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(this.routeDetailsPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                getActionBar().setSelectedNavigationItem(position);
            }
        });


    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    public RoutesResponseDTO getDto() {
        return dto;
    }

    public Integer getSelectedRouteId() {
        return selectedRouteId;
    }

    public void setSelectedRouteId(Integer selectedRouteId) {
        this.selectedRouteId = selectedRouteId;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
//            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
