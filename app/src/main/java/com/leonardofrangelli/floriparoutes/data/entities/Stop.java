package com.leonardofrangelli.floriparoutes.data.entities;

import java.io.Serializable;

/**
 * Created by frangelli on 9/7/14.
 */
public class Stop extends BaseEntity implements Serializable {

    private String name;
    private Integer sequence;
    private Integer routeId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }
}
