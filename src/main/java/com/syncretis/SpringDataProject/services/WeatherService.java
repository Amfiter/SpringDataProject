package com.syncretis.SpringDataProject.services;

import com.syncretis.SpringDataProject.entities.User;
import com.syncretis.SpringDataProject.exceptions.UserException;
import com.syncretis.SpringDataProject.model.OpenWeather;
import com.syncretis.SpringDataProject.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final String key = "aa3c69683b82af8d25510259d657156d";
    private final String url = "https://api.openweathermap.org/";
    private final UserRepository userRepository;

    public WeatherService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public OpenWeather getWeatherByUsername(String username) {
        RestTemplate restTemplate = new RestTemplate();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND));
        String location = user.getCity();
        OpenWeather openWeather = restTemplate.getForObject(url + "data/2.5/weather?q=" + location + "&appid=" + key, OpenWeather.class);
        return openWeather;
    }
    // TODO: "Stavitskii Vladimir"  19.08.2021 => сделать OkHttp 

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
