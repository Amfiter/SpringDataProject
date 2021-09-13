package com.syncretis.SpringDataProject.model;

import java.util.Objects;
import java.util.StringJoiner;

public class NewOpenWeather {

    private String name;
    private String weatherDescription;
    private double temperature;
    private double feelsLike;
    private double temperatureMin;
    private double temperatureMax;
    private double pressure;
    private Long humidity;
    private double windSpeed;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public Long getHumidity() {
        return humidity;
    }

    public void setHumidity(Long humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double wind_speed) {
        this.windSpeed = wind_speed;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", NewOpenWeather.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("weather='" + weatherDescription + "'")
                .add("temperature=" + temperature)
                .add("feelsLike=" + feelsLike)
                .add("temperatureMin=" + temperatureMin)
                .add("temperatureMax=" + temperatureMax)
                .add("pressure=" + pressure)
                .add("humidity=" + humidity)
                .add("windSpeed=" + windSpeed)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewOpenWeather that = (NewOpenWeather) o;
        return Double.compare(that.temperature, temperature) == 0 && Double.compare(that.feelsLike, feelsLike) == 0 && Double.compare(that.temperatureMin, temperatureMin) == 0 && Double.compare(that.temperatureMax, temperatureMax) == 0 && Double.compare(that.pressure, pressure) == 0 && Double.compare(that.windSpeed, windSpeed) == 0 && Objects.equals(name, that.name) && Objects.equals(weatherDescription, that.weatherDescription) && Objects.equals(humidity, that.humidity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weatherDescription, temperature, feelsLike, temperatureMin, temperatureMax, pressure, humidity, windSpeed);
    }
}
