package com.outfit.business.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Service
public class WeatherService {
    private RestTemplate restTemp;
    private WeatherUrl weatherData;

    @Autowired
    public WeatherService(RestTemplate restTemp, WeatherUrl weatherData) {
        this.restTemp = restTemp;
        this.weatherData = weatherData;
    }

    public Weather getWeather(FormAttributes formAttributes) throws IOException {

        var uriComponents = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(weatherData.getUrl())
                .path("")
                .query("q={keyword}&appid={appid}")
                .buildAndExpand(formAttributes.getCity(), weatherData.getApiKey());

        var uriString = uriComponents.toUriString();
        var stringResponseEntity = restTemp.exchange(uriString, HttpMethod.GET, null, String.class);
        var mapper = new ObjectMapper();
        return mapper.readValue(stringResponseEntity.getBody(), Weather.class);
    }
}