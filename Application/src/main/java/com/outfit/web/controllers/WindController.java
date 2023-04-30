package com.outfit.web.controllers;

import com.outfit.business.entities.Wind;
import com.outfit.business.entities.repositories.*;
import com.outfit.business.services.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class WindController {
    private ItemRepository itemRepository;
    private CategoryRepository categoryRepository;
    private ClimateRepository climateRepository;
    private OccasionRepository occasionRepository;
    private WindRepository windRepository;
    private UserService userService;
    @Autowired
    public WindController(ItemRepository itemRepository, CategoryRepository categoryRepository, ClimateRepository climateRepository, OccasionRepository occasionRepository, WindRepository windRepository, UserService userService) {
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

        model.addAttribute("page_title", "Add Wind");
        model.addAttribute("process", "processwind");
    }

    @GetMapping("/addwind")
    public String windForm(Model model) {
        findAll(model);
        model.addAttribute("object", new Wind());
        return "type";
    }

    @PostMapping("/processwind")
    public String processSubject(@Valid @ModelAttribute("object") Wind wind,
                                 BindingResult result,
                                 Model model) {
        findAll(model);
        if (result.hasErrors()) {
            return "type";
        }
        boolean isPresent = windRepository.existsById(wind.getId());
        if (isPresent) {
            var windDB = windRepository.findById(wind.getId()).get();
            windDB.setName(wind.getName().toLowerCase());
            windRepository.save(windDB);
            model.addAttribute("message", "Wind Successfully Updated");
        } else {
            wind.setName(wind.getName().toLowerCase());
            if (windRepository.findByName(wind.getName()) != null) {
                model.addAttribute("message", "You already have a wind called " +
                        wind.getName() + "!" + " Try something else.");
                return "type";
            }
            windRepository.save(wind);
        }
        return "redirect:/";
    }

    @RequestMapping("/detailwind/{id}")
    public String showItemsByWind(@PathVariable("id") long id, Model model) {
        findAll(model);
        var user = userService.getUser();
        model.addAttribute("page_title", windRepository.findById(id).get().getName());

        if (user != null) {//This is true with user
            if (userService.isUser()) {
                model.addAttribute("items", itemRepository.findAllByWind_IdAndUser(id, user));
            }
            if (userService.isAdmin()) {
                model.addAttribute("items", itemRepository.findAllByWind_Id(id));
            }
        } else {
            model.addAttribute("items", itemRepository.findAllByWind_Id(id));
        }
        return "detaillist";
    }

    @RequestMapping("/deletewind/{id}")
    public String deleteWind(@PathVariable("id") long id) {
        windRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/updatewind/{id}")
    public String updateItem(@PathVariable("id") long id, Model model) {
        findAll(model);
        model.addAttribute("page_title", "Update Wind");
        model.addAttribute("object", windRepository.findById(id).get());
        return "type";
    }

    @PostConstruct
    public void fillTables() {
       /* var wind = new Wind();
        wind.setTitle("Light");
        windRepository.save(wind);

        wind = new Wind();
        wind.setTitle("Moderate");
        windRepository.save(wind);

        wind = new Wind();
        wind.setTitle("Severe");
        windRepository.save(wind);

        wind = new Wind();
        wind.setTitle("Periodic");
        windRepository.save(wind);*/
    }
}
