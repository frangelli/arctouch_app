package com.leonardofrangelli.floriparoutes.data.entities;

import java.io.Serializable;
import java.util.List;

/**
 * Created by frangelli on 9/6/14.
 */
public class Route extends  BaseEntity implements Serializable{

    private String shortName;
    private String longName;
    private List<Departure> departures;
    private List<Stop> stops;

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public List<Departure> getDepartures() {
        return departures;
    }

    public void setDepartures(List<Departure> departures) {
        this.departures = departures;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }
}
