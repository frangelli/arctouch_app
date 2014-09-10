package com.leonardofrangelli.floriparoutes.data.entities;

import java.io.Serializable;

/**
 * Created by frangelli on 9/7/14.
 */
public class BaseEntity implements Serializable {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
