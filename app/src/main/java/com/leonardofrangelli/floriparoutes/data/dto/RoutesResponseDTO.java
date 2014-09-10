package com.leonardofrangelli.floriparoutes.data.dto;

import com.leonardofrangelli.floriparoutes.data.entities.Departure;
import com.leonardofrangelli.floriparoutes.data.entities.Route;
import com.leonardofrangelli.floriparoutes.data.entities.Stop;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by frangelli on 9/8/14.
 */
public class RoutesResponseDTO implements Serializable {

    private List<Route> routes;
    private List<Departure> departures;
    private List<Stop> stops;

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    private Map<String, String> selectedRoute;
    private String txtSearchRoute;


    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public List<Departure> getDepartures() {
        return departures;
    }

    public void setDepartures(List<Departure> departures) {
        this.departures = departures;
    }

    public Map<String, String> getSelectedRoute() {
        return selectedRoute;
    }

    public void setSelectedRoute(Map<String, String> selectedRoute) {
        this.selectedRoute = selectedRoute;
    }

    public String getTxtSearchRoute() {
        return txtSearchRoute;
    }

    public void setTxtSearchRoute(String txtSearchRoute) {
        this.txtSearchRoute = txtSearchRoute;
    }
}
