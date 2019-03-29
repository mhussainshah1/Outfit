package com.example.demo.web.controller;

import com.example.demo.business.entities.Item;
import com.example.demo.business.entities.repositories.CategoryRepository;
import com.example.demo.business.entities.repositories.ClimateRepository;
import com.example.demo.business.entities.repositories.ItemRepository;
import com.example.demo.business.entities.repositories.OccasionRepository;
import com.example.demo.business.services.FormAttributes;
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
import java.util.HashSet;
import java.util.Random;
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
    ClimateRepository climateRepository;


    public void findAll(Model model) {
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

        Weather weather = weatherService.getWeather(formAttributes);
        String climate = getClimate(weather.getTemp());
        System.out.println(climate + " " + weather.getTemp() );

        //outfit.add(itemRepository.findAllByClimate(climate));
       // model.addAttribute("items", itemRepository.findAllByClimate(climate));
        model.addAttribute("weatherData", weather);
        return "weatherDetails";
    }

    private String getClimate(double temperature){
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
    public Set<Item> getOutfit() {
        HashSet<Item> outfit = new HashSet<>();
        outfit.add(itemRepository.findByName(""));
        outfit.add(itemRepository.findByName(""));
        outfit.add(itemRepository.findByName(""));
        outfit.add(itemRepository.findByName(""));

        // Initialization of random numbers specific to burrito options

        Random a = new Random();
        Random b = new Random();
        Random j = new Random();
        Random s = new Random();
        Random t = new Random();

        //String array of Options
        String[] tops = {"White Short-sleeve T-Shirt, ", "Grey Short-sleeve Sports Shirt, ", "Blue T-Shirt, ", "Blue long Sleeve T-shirt, ", "Blue Short-sleeve Science Shirt, ", "Grey Long Sleeve, "};
        String[] bottoms = {"Cargo khaki, ", "Jeans Blue, ", "Khaki Brown, ", "Snow Gray, ", "Shorts Blue, ", "Shorts Grey,", "Shorts Khaki"};
        String[] shoes = {"Black Tan Timberland", "Grey long Socks", "Nike-Green-Winter-Shoes", "Winter-Socks-Grey-White", "Winter-Socks-Grey-Yellow", "Nhan's All-weather Sandals", "Warm Midhigh Socks"};
        String[] jackets = {"Black Dark-Grey Jacket", "Black Grey Jacket", "Black Light-Grey Jacket", "Black Jacket", "Green Grey Jacket", "Northface Black Jacket", "Black Nike Windbreaker", "White Navy-Blue Windbreaker Columbia"};
        String[] accessories = {"Winter", "Spring", "Summer", "Fall"};

        // Int list of bounds equal to the length of the attribute list to burrito options

        int alength = accessories.length;
        int blength = bottoms.length;
        int jlength = jackets.length;
        int slength = shoes.length;
        int tlength = tops.length;

        // Int list of random variables specific to burrito options
        int ax = a.nextInt(alength + 2);
        int bx = b.nextInt(blength + 3);
        int jx = j.nextInt(jlength + 2);
        int sx = s.nextInt(slength + 2);
        int tx = t.nextInt(tlength + 2);

// if (item.temp = hot){


// }
        for (int i = 1; i < 8; i++) {
            // int j = i + 1;
            System.out.println();
            System.out.print("Outfit " + i + ": ");
            System.out.print(tops[t.nextInt(6)]);
            System.out.print(bottoms[b.nextInt(7)] + " ");
            System.out.print(shoes[s.nextInt(3)] + ", ");
            System.out.print(jackets[j.nextInt(8)] + ", ");
            System.out.print(accessories[a.nextInt(4)] + " ");
            System.out.println();
//                System.out.println();
            /* Tried to print an Array of Arrays, didn't like that one, next I'd attempt an array list of arrays, but the above code will do*/
            // System.out.println("Burrito "+ i + ": "+ rice[rx] +" "+ meat[mx]+"," + beans[bx]);
        }

        return outfit;
    }
}