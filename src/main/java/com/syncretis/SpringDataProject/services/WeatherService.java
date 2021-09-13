package com.syncretis.SpringDataProject.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.syncretis.SpringDataProject.exceptions.UserException;
import com.syncretis.SpringDataProject.model.NewOpenWeather;
import com.syncretis.SpringDataProject.model.OpenWeather;
import com.syncretis.SpringDataProject.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WeatherService {

    private final long KELVIN_ZERO = 273L;
    private final String KEY = "f0d7c77842669fcea2015fdeb04698ea";
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public WeatherService(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    public NewOpenWeather getWeatherByUsernameOkHttp(String username) {
        OpenWeather openWeather = null;
        NewOpenWeather weather = null;
        OkHttpClient client = new OkHttpClient();
        //сделать селект по городу где есть такой юзер
        String city = userRepository.findByUsername(username).orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND)).getCity();
        HttpUrl mySearchUrl = urlBuilder(city, KEY);
        Request request = new Request.Builder().url(mySearchUrl).build();
        try {
            Response response = client.newCall(request).execute();
            openWeather = objectMapper.readValue(response.body().string(), OpenWeather.class);
            weather = weatherMapper(openWeather);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weather;
    }

    public HttpUrl urlBuilder(String city,String key){
        return new HttpUrl.Builder()
                .scheme("https")
                .host("api.openweathermap.org")
                .addPathSegment("data")
                .addPathSegment("2.5")
                .addPathSegment("weather")
                .addQueryParameter("q", city)
                .addQueryParameter("appid", key)
                .build();
    }


    public NewOpenWeather weatherMapper(OpenWeather openWeather) {
        NewOpenWeather newOpenWeather = new NewOpenWeather();
        newOpenWeather.setWeatherDescription(openWeather.getWeather().get(0).getDescription());
        newOpenWeather.setTemperature(Math.round(openWeather.getMain().getTemp() - KELVIN_ZERO));
        newOpenWeather.setTemperatureMin(Math.round(openWeather.getMain().getTempMin() - KELVIN_ZERO));
        newOpenWeather.setTemperatureMax(Math.round(openWeather.getMain().getTempMax() - KELVIN_ZERO));
        newOpenWeather.setHumidity(openWeather.getMain().getHumidity());
        newOpenWeather.setPressure(openWeather.getMain().getPressure());
        newOpenWeather.setFeelsLike(Math.round(openWeather.getMain().getFeelsLike() - KELVIN_ZERO));
        newOpenWeather.setWindSpeed(openWeather.getWind().getSpeed());
        newOpenWeather.setName(openWeather.getName());
        return newOpenWeather;
    }
}
