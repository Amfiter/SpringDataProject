package com.syncretis.SpringDataProject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
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

    public void setMain(String main) {
        this.main = main;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
