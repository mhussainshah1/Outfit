package com.example.demo.web.controller;

import com.example.demo.business.entities.repositories.CategoryRepository;
import com.example.demo.business.entities.repositories.ClimateRepository;
import com.example.demo.business.entities.repositories.OccasionRepository;
import com.example.demo.business.services.FormAttributes;
import com.example.demo.business.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Controller
public class WeatherController {

    @Autowired
    RestTemplate restTemp;

    @Autowired
    WeatherService weatherService;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    OccasionRepository occasionRepository;

    @Autowired
    ClimateRepository climateRepository;


    public void findAll(Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("climates", climateRepository.findAll());
        model.addAttribute("occasions", occasionRepository.findAll());
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
        model.addAttribute("weatherData", weatherService.getWeather(formAttributes));
        return "weatherDetails";
    }

}