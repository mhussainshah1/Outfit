package com.outfit.web.controllers;

import com.outfit.business.entities.Item;
import com.outfit.business.entities.User;
import com.outfit.business.entities.repositories.*;
import com.outfit.business.services.FormAttributes;
import com.outfit.business.services.UserService;
import com.outfit.business.services.Weather;
import com.outfit.business.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import jakarta.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class WeatherController {
    private RestTemplate restTemp;
    private WeatherService weatherService;
    private ItemRepository itemRepository;
    private CategoryRepository categoryRepository;
    private OccasionRepository occasionRepository;
    private WindRepository windRepository;
    private ClimateRepository climateRepository;
    private UserService userService;
    @Autowired
    public WeatherController(RestTemplate restTemp, WeatherService weatherService, ItemRepository itemRepository, CategoryRepository categoryRepository, OccasionRepository occasionRepository, WindRepository windRepository, ClimateRepository climateRepository, UserService userService) {
        this.restTemp = restTemp;
        this.weatherService = weatherService;
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.occasionRepository = occasionRepository;
        this.windRepository = windRepository;
        this.climateRepository = climateRepository;
        this.userService = userService;
    }

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
    public String getWeather(@Valid @ModelAttribute("formAttributes") FormAttributes formAttributes,
                             BindingResult result,
                             //@RequestParam("city") String city,
                             Model model)
            throws IOException {
        findAll(model);
        if (result.hasErrors()) {
            User user = userService.getUser();
            model.addAttribute("items", itemRepository.findAllByUser(user));
            return "list";
        }
        model.addAttribute("page_title", formAttributes.getCity());

        var weather = weatherService.getWeather(formAttributes);
        model.addAttribute("weatherData", weather);
        model.addAttribute("items", getOutfit(weather));
        return "weatherlist";
    }

    private String getClimate(double temperature) {
        String climate;
        long temp = Math.round(temperature);
        if (temp < 20) {
            climate = "Cold";
        } else if (temp >= 20 && temp < 32) {
            climate = "Moderate";
        } else {
            climate = "Hot";
        }
        return climate;
    }

    private String getWind(Number windSpeed) {
        String wind;
        long speed = windSpeed.longValue();
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

        var categories = categoryRepository.findAll();
        var temperature = Double.parseDouble(weather.getCelsiusTemperature(weather.getTemp()));
        var climateString = getClimate(temperature);
        var climate = climateRepository.findByName(climateString);

        var windString = getWind(weather.getWindSpeed());
        var wind = windRepository.findByName(windString);

        var user = userService.getUser();

        System.err.println(climateString + " " + weather.getTemp());
        System.err.println(windString + " " + weather.getWindSpeed());

        var outfit = new HashSet<Item>();
        for (var category : categories) {
            //pick one item from the below list

            List<Item> list = new ArrayList<>();
            if (userService.isUser()) {
                list = itemRepository.findAllByCategoryAndClimateAndUser(category, climate, user);
            }
            if (userService.isAdmin()) {
                list = itemRepository.findAllByCategoryAndClimate(category, climate);
            }
            if (!list.isEmpty()) {
                var randomid = (int) (Math.random() * list.size());
                outfit.add(list.get(randomid));
            }
        }
        return outfit;
    }
}