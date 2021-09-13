package com.syncretis.SpringDataProject.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.HttpUrl;
import com.syncretis.SpringDataProject.entities.Role;
import com.syncretis.SpringDataProject.entities.User;
import com.syncretis.SpringDataProject.model.*;
import com.syncretis.SpringDataProject.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith({MockitoExtension.class})
class WeatherServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    ObjectMapper objectMapper;

    @InjectMocks
    WeatherService weatherService;


    @Test
    @DisplayName("shouldReturnNewOpenWeatherString")
    void getWeatherByUsernameOkHttp() throws JsonProcessingException {
        //given
        Wind wind = new Wind();
        wind.setSpeed(2.56D);

        Main main = new Main();
        main.setTemp(295.0D);
        main.setTempMax(295.0D);
        main.setTempMin(295.0D);
        main.setFeelsLike(295.0D);
        main.setPressure(1050.0D);
        main.setHumidity(77L);

        List<Weather> weatherList = new ArrayList<>();

        Weather weather = new Weather();
        weather.setDescription("broken clouds");
        weather.setMain("Clouds");
        weatherList.add(weather);

        OpenWeather openWeather = new OpenWeather();
        openWeather.setWeather(weatherList);
        openWeather.setWind(wind);
        openWeather.setMain(main);
        openWeather.setName("Tomsk Oblast");

        Set<Role> roles = new HashSet<>();
        Role role = new Role(1L,"ADMIN");
        roles.add(role);

        User user = new User();
        user.setId(12L);
        user.setUsername("Vladimir");
        user.setPassword("123");
        user.setCity("Tomsk Oblast");
        user.setRoles(roles);

        NewOpenWeather expected = new NewOpenWeather();
        expected.setWeatherDescription("broken clouds");
        expected.setName("Tomsk Oblast");
        expected.setPressure(1050.0D);
        expected.setFeelsLike(22.0D);
        expected.setHumidity(77L);
        expected.setTemperature(22.0D);
        expected.setTemperatureMin(22.0D);
        expected.setTemperatureMax(22.0D);
        expected.setWindSpeed(2.56D);

        //when
        Mockito.when(userRepository.findByUsername(Mockito.any())).thenReturn(Optional.of(user));
        Mockito.when(objectMapper.readValue(Mockito.anyString(),Mockito.eq(OpenWeather.class))).thenReturn(openWeather);
        NewOpenWeather actual = weatherService.getWeatherByUsernameOkHttp(user.getUsername());

        //then
        Mockito.verify(userRepository).findByUsername(Mockito.any());
        Mockito.verify(objectMapper).readValue(Mockito.anyString(),Mockito.eq(OpenWeather.class));
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    @DisplayName("shouldReturnNewOpenWeather")
    void weatherMapper() {
        //given
        Wind wind = new Wind();
        wind.setSpeed(2.56D);

        Main main = new Main();
        main.setTemp(300.0D);
        main.setTempMax(300.0D);
        main.setTempMin(300.0D);
        main.setFeelsLike(300.0D);
        main.setPressure(1050.0D);
        main.setHumidity(76L);

        List<Weather> weatherList = new ArrayList<>();

        Weather weather = new Weather();
        weather.setDescription("broken clouds");
        weather.setMain("Clouds");
        weatherList.add(weather);

        OpenWeather openWeather = new OpenWeather();
        openWeather.setWeather(weatherList);
        openWeather.setWind(wind);
        openWeather.setMain(main);
        openWeather.setName("Tomsk Oblast");

        NewOpenWeather expected = new NewOpenWeather();
        expected.setWeatherDescription("broken clouds");
        expected.setName("Tomsk Oblast");
        expected.setPressure(1050.0D);
        expected.setFeelsLike(27.0D);
        expected.setHumidity(76L);
        expected.setTemperature(27.0D);
        expected.setTemperatureMin(27.0D);
        expected.setTemperatureMax(27.0D);
        expected.setWindSpeed(2.56D);

        //when
        NewOpenWeather actual = weatherService.weatherMapper(openWeather);
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("shouldReturnHttpUrl")
    void urlBuilder(){
        //given
       HttpUrl expected =  new HttpUrl.Builder()
                .scheme("https")
                .host("api.openweathermap.org")
                .addPathSegment("data")
                .addPathSegment("2.5")
                .addPathSegment("weather")
                .addQueryParameter("q", "city")
                .addQueryParameter("appid", "token")
                .build();
        //when
        HttpUrl actual = weatherService.urlBuilder("city","token");
        //then
        assertThat(actual).isEqualTo(expected);
    }
}