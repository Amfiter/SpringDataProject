package com.syncretis.SpringDataProject.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeather {

    private String name;
    private Main main;
    private Wind wind;
    private List<Weather> weather;

    public String getName() {
        return name;
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }
}
