package com.syncretis.SpringDataProject.controllers;

import com.syncretis.SpringDataProject.model.NewOpenWeather;
import com.syncretis.SpringDataProject.services.WeatherService;
import com.syncretis.SpringDataProject.util.PrettyLook;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/weather")
public class WeatherController {

    private final WeatherService weatherService;
    private final PrettyLook prettyLook;

    public WeatherController( WeatherService weatherService, PrettyLook prettyLook) {
        this.weatherService = weatherService;
        this.prettyLook = prettyLook;
    }

    @GetMapping(path="/okhttp",produces = MediaType.APPLICATION_JSON_VALUE)
    public String getWeatherOkHttp(@CurrentSecurityContext(expression="authentication?.name") String username) {
        NewOpenWeather weatherByUsernameOkHttp = weatherService.getWeatherByUsernameOkHttp(username);
        return  prettyLook.weatherPretty(weatherByUsernameOkHttp);
    }
}
