package com.leonardofrangelli.floriparoutes.data.entities;

import java.io.Serializable;

/**
 * Created by frangelli on 9/7/14.
 */
public class Departure extends BaseEntity implements Serializable {

    private String calendar;
    private String time;

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
