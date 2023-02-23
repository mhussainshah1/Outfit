package com.outfit.web.controllers;

import com.outfit.business.entities.Climate;
import com.outfit.business.entities.repositories.*;
import com.outfit.business.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

@Controller
public class ClimateController {
    private ItemRepository itemRepository;
    private CategoryRepository categoryRepository;
    private ClimateRepository climateRepository;
    private OccasionRepository occasionRepository;
    private WindRepository windRepository;
    private UserService userService;
    @Autowired
    public ClimateController(ItemRepository itemRepository, CategoryRepository categoryRepository, ClimateRepository climateRepository, OccasionRepository occasionRepository, WindRepository windRepository, UserService userService) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.climateRepository = climateRepository;
        this.occasionRepository = occasionRepository;
        this.windRepository = windRepository;
        this.userService = userService;
    }

    public void findAll(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("climates", climateRepository.findAll());
        model.addAttribute("occasions", occasionRepository.findAll());
        model.addAttribute("winds", windRepository.findAll());

        model.addAttribute("page_title", "Add Climate");
        model.addAttribute("process", "processclimate");
    }

    @GetMapping("/addclimate")
    public String climateForm(Model model) {
        findAll(model);
        model.addAttribute("object", new Climate());
        return "type";
    }

    @PostMapping("/processclimate")
    public String processSubject(@Valid @ModelAttribute("object") Climate climate,
                                 BindingResult result,
                                 Model model) {
        findAll(model);
        if (result.hasErrors()) {
            return "type";
        }
        boolean isPresent = categoryRepository.existsById(climate.getId());
        if (isPresent) {
            Climate climateDB = climateRepository.findById(climate.getId()).get();
            climateDB.setName(climate.getName().toLowerCase());
            climateRepository.save(climateDB);
            model.addAttribute("message", "Climate Successfully Updated");
        } else {
            climate.setName(climate.getName().toLowerCase());
            if (climateRepository.findByName(climate.getName()) != null) {
                model.addAttribute("message", "You already have a climate called " +
                        climate.getName() + "!" + " Try something else.");
                return "type";
            }
            climateRepository.save(climate);
        }
        return "redirect:/";
    }

    @RequestMapping("/detailclimate/{id}")
    public String showOutfitsByClimate(@PathVariable("id") long id, Model model) {
        findAll(model);
        var user = userService.getUser();
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

    @RequestMapping("/deleteclimate/{id}")
    public String deleteClimate(@PathVariable("id") long id) {
        climateRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/updateclimate/{id}")
    public String updateItem(@PathVariable("id") long id, Model model) {
        findAll(model);
        model.addAttribute("page_title", "Update Climate");
        model.addAttribute("object", climateRepository.findById(id).get());
        return "type";
    }

    @PostConstruct
    public void fillTables() {
       /* var climate = new Climate();
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

