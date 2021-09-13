package com.syncretis.SpringDataProject.util;

import com.syncretis.SpringDataProject.model.NewOpenWeather;
import org.springframework.stereotype.Service;

@Service
public class PrettyLook {

    public String weatherPretty(NewOpenWeather newOpenWeather) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hello weather in " +newOpenWeather.getName())
                .append(" is " + newOpenWeather.getWeatherDescription() + "\n")
                .append("temperature: " + newOpenWeather.getTemperature() + " C°\n")
                .append("temperature in minimum: " + newOpenWeather.getTemperatureMin() + " C°\n")
                .append("temperature in maximum: " + newOpenWeather.getTemperatureMax() + " C°\n")
                .append("weather feels like: " + newOpenWeather.getFeelsLike() + " C°\n")
                .append("pressure: " + newOpenWeather.getPressure() + " mb\n")
                .append("humidity: " + newOpenWeather.getHumidity() + " %\n")
                .append("wind speed: " + newOpenWeather.getWindSpeed() + " m/s\n");
        return stringBuilder.toString();
    }
}
