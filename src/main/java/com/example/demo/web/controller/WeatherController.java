package com.example.demo.web.controller;

import com.example.demo.business.entities.*;
import com.example.demo.business.entities.repositories.*;
import com.example.demo.business.services.FormAttributes;
import com.example.demo.business.services.UserService;
import com.example.demo.business.services.Weather;
import com.example.demo.business.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class WeatherController {

    @Autowired
    RestTemplate restTemp;

    @Autowired
    WeatherService weatherService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    OccasionRepository occasionRepository;

    @Autowired
    WindRepository windRepository;

    @Autowired
    ClimateRepository climateRepository;

    @Autowired
    UserService userService;

    public void findAll(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("climates", climateRepository.findAll());
        model.addAttribute("occasions", occasionRepository.findAll());
        model.addAttribute("winds", windRepository.findAll());
    }

    @GetMapping("/weather")
    public String CityForm(Model model) {
        model.addAttribute("city", new FormAttributes());
        return "list";
    }

    @PostMapping("/weather")
    public String getWeather(Model model, @ModelAttribute FormAttributes formAttributes)
            throws IOException {
        findAll(model);
        Weather weather = weatherService.getWeather(formAttributes);
        model.addAttribute("items", getOutfit(weather));
        model.addAttribute("weatherData", weather);
        return "weatherlist";
    }

    private String getClimate(double temperature) {
        String climate;
        long temp = Math.round(temperature);
        if (temp < 25) {
            climate = "Cold";
        } else if (temp >= 25 && temp < 32) {
            climate = "Moderate";
        } else {
            climate = "Hot";
        }
        return climate;
    }

    private String getWind(double windSpeed) {
        String wind;
        long speed = Math.round(windSpeed);
        if (speed < 10) {
            wind = "Light";
        } else if (speed >= 10 && speed < 38) {
            wind = "Moderate";
        } else {
            wind = "High";
        }
        return wind;
    }

    private Set<Item> getOutfit(Weather weather) {

        Iterable<Category> categories = categoryRepository.findAll();

        String climateString = getClimate(weather.getTemp());
        Climate climate = climateRepository.findByName(climateString);

        String windString = getWind(weather.getWindSpeed());
        Wind wind = windRepository.findByName(windString);

        User user = userService.getUser();

        System.out.println(climateString + " " + weather.getTemp());
        System.out.println(windString + " " + weather.getWindSpeed());

        Set<Item> outfit = new HashSet<>();
        for (Category category : categories) {
            //pick one item from the below list

            List<Item> list = new ArrayList<>();
            if (userService.isUser()) {
                list = itemRepository.findAllByCategoryAndClimateAndUser(category, climate, user);
            }
            if (userService.isAdmin()) {
                list = itemRepository.findAllByCategoryAndClimate(category, climate);
            }

            if (!list.isEmpty()) {
                int randomid = (int) (Math.random() * list.size());
                outfit.add(list.get(randomid));
            }
        }
        return outfit;
    }
}