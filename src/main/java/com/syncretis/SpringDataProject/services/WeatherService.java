package com.syncretis.SpringDataProject.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.syncretis.SpringDataProject.entities.User;
import com.syncretis.SpringDataProject.exceptions.UserException;
import com.syncretis.SpringDataProject.model.NewOpenWeather;
import com.syncretis.SpringDataProject.model.OpenWeather;
import com.syncretis.SpringDataProject.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class WeatherService {

    private final static long KELVIN_ZERO = 273;
    private final String key = "aa3c69683b82af8d25510259d657156d";
    private final String url = "https://api.openweathermap.org/";
    private final UserRepository userRepository;

    public WeatherService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public OpenWeather getWeatherByUsernameRestTemplate(String username) {
        RestTemplate restTemplate = new RestTemplate();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND));
        String location = user.getCity();
        OpenWeather openWeather = restTemplate.getForObject(url + "data/2.5/weather?q=" + location + "&appid=" + key, OpenWeather.class);
        return openWeather;
    }

    public String getWeatherByUsernameOkHttp(String username) {
        OpenWeather openWeather = null;
        NewOpenWeather weather = null;
        String weatherPretty = null;
        OkHttpClient client = new OkHttpClient();
        String city = userRepository.findByUsername(username).orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND)).getCity();
        String token = "f0d7c77842669fcea2015fdeb04698ea";
        HttpUrl mySearchUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("api.openweathermap.org")
                .addPathSegment("data")
                .addPathSegment("2.5")
                .addPathSegment("weather")
                .addQueryParameter("q", city)
                .addQueryParameter("appid", token)
                .build();
        Request request = new Request.Builder().url(mySearchUrl).build();
        try {
            Response response = client.newCall(request).execute();
            ObjectMapper mapper = new ObjectMapper();
            openWeather = mapper.readValue(response.body().string(), OpenWeather.class);
            weather = weatherMapper(openWeather);
            weatherPretty = weatherPretty(weather, username);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherPretty;
    }

    public NewOpenWeather weatherMapper(OpenWeather openWeather) {
        NewOpenWeather newOpenWeather = new NewOpenWeather();
        newOpenWeather.setWeather(openWeather.getWeather().get(0).getDescription());
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

    public String weatherPretty(NewOpenWeather newOpenWeather, String username) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hello weather in " +newOpenWeather.getName())
                .append(" is " + newOpenWeather.getWeather() + "\n")
                .append("temperature: " + newOpenWeather.getTemperature() + " C°\n")
                .append("temperature in minimum: " + newOpenWeather.getTemperatureMin() + " C°\n")
                .append("temperature in maximum: " + newOpenWeather.getTemperatureMax() + " C°\n")
                .append("weather feels like: " + newOpenWeather.getFeelsLike() + " C°\n")
                .append("pressure: " + newOpenWeather.getPressure() + " mb\n")
                .append("humidity: " + newOpenWeather.getHumidity() + " %\n")
                .append("wind speed: " + newOpenWeather.getWindSpeed() + " m/s\n")
                .append("Good day " + username + "!");
        return stringBuilder.toString();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
