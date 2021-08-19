package com.syncretis.SpringDataProject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Weather {

    @JsonProperty("main")
    private String main;
    @JsonProperty("description")
    private String description;

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }
}
