package com.example.demo.web.controller;

import com.example.demo.business.entities.Climate;
import com.example.demo.business.entities.User;
import com.example.demo.business.entities.repositories.*;
import com.example.demo.business.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@Controller
public class ClimateController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ClimateRepository climateRepository;

    @Autowired
    OccasionRepository occasionRepository;

    @Autowired
    WindRepository windRepository;

    @Autowired
    UserService userService;

    public void findAll(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("climates", climateRepository.findAll());
        model.addAttribute("occasions", occasionRepository.findAll());
        model.addAttribute("winds", windRepository.findAll());
    }

    @GetMapping("/addclimate")
    public String climateForm(Model model) {
        findAll(model);
        model.addAttribute("climate", new Climate());
        return "climate";
    }

    @PostMapping("/processclimate")
    public String processSubject(@Valid Climate climate,
                                 BindingResult result,
                                 Model model) {
        findAll(model);
        if (result.hasErrors()) {
            return "climate";
        }
        climate.setName(climate.getName().toLowerCase());
        if (climateRepository.findByName(climate.getName()) != null) {
            model.addAttribute("message", "You already have a climate called " +
                    climate.getName() + "!" + " Try something else.");
            return "climate";
        }
        climateRepository.save(climate);
        return "redirect:/";
    }

    @RequestMapping("/detailclimate/{id}")
    public String showOutfitsByClimate(@PathVariable("id") long id, Model model) {
        findAll(model);
        User user = userService.getUser();
        model.addAttribute("page_title", climateRepository.findById(id).get().getName());

        if (user != null) {//This is true with user
            if (userService.isUser()) {
                model.addAttribute("items", itemRepository.findAllByClimate_IdAndUser(id, user));
            }
            if (userService.isAdmin()) {
                model.addAttribute("items", itemRepository.findAllByClimate_Id(id));
            }
        } else {
            model.addAttribute("items", itemRepository.findAllByClimate_Id(id));
        }
        return "detaillist";
    }

    @PostConstruct
    public void fillTables() {
       /* Climate climate = new Climate();
        climate.setTitle("Hot");
        climateRepository.save(climate);

        climate = new Climate();
        climate.setTitle("Mild");
        climateRepository.save(climate);

        climate = new Climate();
        climate.setTitle("Cold");
        climateRepository.save(climate);

        climate = new Climate();
        climate.setTitle("Rainy");
        climateRepository.save(climate);*/
    }
}

