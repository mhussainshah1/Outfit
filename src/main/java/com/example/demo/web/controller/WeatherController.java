package com.example.demo.web.controller;

import com.example.demo.web.Weather;
import com.example.demo.web.WeatherService;
import com.example.demo.web.WeatherUrl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


import java.io.IOException;

@Controller
public class WeatherController {

    @Autowired
    RestTemplate restTemp;

    @Autowired
    private WeatherUrl weatherData;

    @Autowired
    WeatherService weatherService;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/weather")
    public String CityForm(Model model) {
        model.addAttribute("city", new String());
        return "list";
    }

    @PostMapping("/weather")
    public String getWeather(Model model, @ModelAttribute("city") String city)
            throws IOException {

        //Weather weather = weatherService.getWeather(city);
        //todo move below code into WeatherService Class and delete FormCityAttribute, WeatherUrl

        UriComponents uriComponents = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(weatherData.getUrl())
                .path("")
                .query("q={keyword}&appid={appid}")
                .buildAndExpand(city, weatherData.getApiKey());

        String uri = uriComponents.toUriString();
        ResponseEntity<String> resp = restTemp.exchange(uri, HttpMethod.GET, null, String.class);
        ObjectMapper mapper = new ObjectMapper();
        Weather weather = mapper.readValue(resp.getBody(), Weather.class);
        model.addAttribute("weatherData", weather);
//        model.addAttribute("weatherData",weatherService.getWeather(city));
        return "weatherDetails";
    }

}