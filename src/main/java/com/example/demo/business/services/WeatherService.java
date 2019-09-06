package com.example.demo.business.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Service
public class WeatherService {

    @Autowired
    RestTemplate restTemp;

    @Autowired
    private WeatherUrl weatherData;

    public Weather getWeather(FormAttributes formAttributes) throws IOException {

        var uriComponents = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(weatherData.getUrl())
                .path("")
                .query("q={keyword}&appid={appid}")
                .buildAndExpand(formAttributes.getCity(), weatherData.getApiKey());

        var uriString = uriComponents.toUriString();
        ResponseEntity<String> resp = restTemp.exchange(uriString, HttpMethod.GET, null, String.class);
        var mapper = new ObjectMapper();
        return mapper.readValue(resp.getBody(), Weather.class);
    }
}