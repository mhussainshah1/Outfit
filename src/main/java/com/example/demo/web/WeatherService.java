package com.example.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Service
public class WeatherService {
<<<<<<< HEAD
   /* private String url;
    private String apiKey;

    *//*@Autowired
    RestTemplate restTemp;

    @Autowired
    private WeatherUrl weatherData;*//*

    @Autowired
    public WeatherService( @Value("${weather.url}") String url,
                           @Value("${weather.apikey}") String apikey){
        this.url=url;
        this.apiKey=url;
    }

    *//*@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public Weather getWeather(String city) throws IOException {
        UriComponents uriComponents = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(url)
                .path("")
                .query("q={keyword}&appid={appid}")
                .buildAndExpand(city, apiKey);
        String uri = uriComponents.toUriString();
        ResponseEntity<String> resp = restTemp.exchange(uri, HttpMethod.GET, null, String.class);
        ObjectMapper mapper = new ObjectMapper();
       return mapper.readValue(resp.getBody(), Weather.class);
    }*/
=======

>>>>>>> 9f4be5ae8a107b189bf2f5f713836fb39c2ca602
}
