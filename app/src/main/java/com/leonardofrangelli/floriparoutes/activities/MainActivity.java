package com.leonardofrangelli.floriparoutes.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.leonardofrangelli.floriparoutes.adapters.RoutesAdapter;
import com.leonardofrangelli.floriparoutes.common.DialogBuilder;
import com.leonardofrangelli.floriparoutes.data.entities.Route;
import com.leonardofrangelli.floriparoutes.R;
import com.leonardofrangelli.floriparoutes.listeners.RouteClickListener;
import com.leonardofrangelli.floriparoutes.tasks.GetStopsByRouteTask;
import com.leonardofrangelli.floriparoutes.tasks.SearchRouteTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends Activity implements SearchableActivity<Route>{

    private EditText txtRouteName;
    private Button btnSearchRoute;
    private ListView routesListView;
    private TextView emptyListMessage;
    private Integer selectedRouteId;
    private Map<String, String> selectedRouteMap;
    private final int GET_STREET_NAME = 1;
    private ImageView emptyListImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViewComponents();
        final ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.exit_the_app:
                DialogFragment dialog = new ExitDialog();
                dialog.show(getFragmentManager(), "dialog");
                return true;
            case R.id.search_by_location:

                Intent mapIntent = new Intent(this,MapActivity.class);
                startActivityForResult(mapIntent, GET_STREET_NAME);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GET_STREET_NAME) {
            if (resultCode == RESULT_OK) {
                String streetName = data.getStringExtra("streetName");
                txtRouteName.setText(streetName);
            }
        }
    }

    /**
     * Initialize all the view components
     * like Buttons, EditTexts etc...
     */
    private void initializeViewComponents() {
        this.btnSearchRoute = (Button) findViewById(R.id.btnSearchRoute);
        this.txtRouteName = (EditText) findViewById(R.id.txtRouteName);
        this.txtRouteName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchRoute(textView);
                    handled = true;
                }
                return handled;

            }
        });
        this.emptyListMessage = (TextView) findViewById(R.id.emptyListMessage);
        this.emptyListImage = (ImageView) findViewById(R.id.handEmptyIndicator);
        this.routesListView = (ListView) findViewById(R.id.routesListView);
        //Listener for onItemClick in the list
        this.routesListView.setOnItemClickListener(new RouteClickListener(this));
    }

    /**
     * btnSearchRoute onClick Listener
     */
    public void searchRoute(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.txtRouteName.getWindowToken(),0);

        DialogBuilder.showProgressDialog("Wait a moment..\nWe're searching your routes","Searching...",this);
        new SearchRouteTask().execute(this);
    }

    public Map<String, String> getSelectedRouteMap() {
        return selectedRouteMap;
    }

    public void setSelectedRouteMap(Map<String, String> selectedRouteMap) {
        this.selectedRouteMap = selectedRouteMap;
    }

    @Override
    public String getSearchValue() {
        return String.valueOf(this.txtRouteName.getText());
    }

    @Override
    public void onReceiveListOfResults(List<Route> result) {
        if (result == null || result.isEmpty()) {
            this.hideListView();
        } else {
            this.showRoutes(result);
        }
        DialogBuilder.closeProgressDialog();
    }

    /**
     * Set the adapter for the ListView
     * and set the list visible or not
     * @param routes
     */
    private void showRoutes(List<Route> routes) {
        if(routes != null && !routes.isEmpty()) {
            List<Map<String,String>> dataList = parseRoutesToMap(routes);
            ListAdapter adapter = new RoutesAdapter(this,dataList);
            this.showListView(adapter);
        } else {
            this.hideListView();
        }
    }

    /**
     * Sets the ListView visible
     * @param adapter
     */
    private void showListView(ListAdapter adapter) {
        this.routesListView.setAdapter(adapter);
        this.emptyListMessage.setVisibility(View.GONE);
        this.emptyListImage.setVisibility(View.GONE);
        this.routesListView.setVisibility(View.VISIBLE);

    }

    /**
     * Parse the List of routes to Map
     * to fit the RoutesAdapter
     * @param routes
     * @return
     */
    private List<Map<String, String>> parseRoutesToMap(List<Route> routes) {
        List<Map<String,String>> dataList = new ArrayList<Map<String, String>>();

        for (Route route : routes) {
            Map<String,String> routeMap = new HashMap<String, String>();
            routeMap.put("id",String.valueOf(route.getId()));
            routeMap.put("longName",route.getLongName());
            routeMap.put("shortName",route.getShortName());
            dataList.add(routeMap);
        }

        return dataList;
    }


    /**
     * Hides the ListView
     */
    private void hideListView() {
        this.routesListView.setAdapter(null);
        this.routesListView.setVisibility(View.GONE);
        this.emptyListImage.setVisibility(View.VISIBLE);
        this.emptyListMessage.setVisibility(View.VISIBLE);

    }


    public Integer getSelectedRouteId() {
        return selectedRouteId;
    }

    public void setSelectedRouteId(Integer selectedRouteId) {
        this.selectedRouteId = selectedRouteId;
    }

    public ListView getRoutesListView() {
        return routesListView;
    }

    @Override
    protected void onPause() {
        DialogBuilder.closeProgressDialog();
        super.onPause();
    }

    @Override
    protected void onPostResume() {
        DialogBuilder.closeProgressDialog();
        super.onResume();
    }


}
