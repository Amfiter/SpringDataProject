package com.syncretis.SpringDataProject.controllers;

import com.syncretis.SpringDataProject.model.OpenWeather;
import com.syncretis.SpringDataProject.services.WeatherService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path = "api/weather")
public class WeatherController {

    private final RestTemplate restTemplate;
    private final WeatherService weatherService;

    public WeatherController(RestTemplate restTemplate, WeatherService weatherService) {
        this.restTemplate = restTemplate;
        this.weatherService = weatherService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public OpenWeather getWeather(@CurrentSecurityContext(expression="authentication?.name")
                                              String username) {
        return weatherService.getWeatherByUsername(username);
    }

    @GetMapping(value="/openweather",produces = MediaType.APPLICATION_JSON_VALUE)
    public String getOpenWeather() {
        String location = "London";
        String key = "aa3c69683b82af8d25510259d657156d";
        String url = "http://api.openweathermap.org/data/2.5/weather?q="+location+"&appid="+key;

        //Calling OpenWeather API
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String output = response.getBody();
        return output;
    }
}
