package com.syncretis.SpringDataProject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Wind {

    @JsonProperty("speed")
    private double speed;

    public double getSpeed() {
        return speed;
    }
}
