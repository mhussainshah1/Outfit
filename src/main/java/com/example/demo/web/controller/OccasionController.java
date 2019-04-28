package com.example.demo.web.controller;

import com.example.demo.business.entities.Occasion;
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
public class OccasionController {

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

    @GetMapping("/addoccasion")
    public String occasionForm(Model model) {
        findAll(model);
        model.addAttribute("occasion", new Occasion());
        return "occasion";
    }

    @PostMapping("/processoccasion")
    public String processSubject(@Valid Occasion occasion,
                                 BindingResult result,
                                 Model model) {
        findAll(model);
        if (result.hasErrors()) {
            return "occasion";
        }
        occasion.setName(occasion.getName().toLowerCase());
        if (occasionRepository.findByName(occasion.getName()) != null) {
            model.addAttribute("message", "You already have a occasion called " +
                    occasion.getName() + "!" + " Try something else.");
            return "occasion";
        }
        occasionRepository.save(occasion);
        return "redirect:/";
    }

    @RequestMapping("/detailoccasion/{id}")
    public String showOutfitsByOccasion(@PathVariable("id") long id, Model model) {
        findAll(model);
        User user = userService.getUser();
        model.addAttribute("page_title", occasionRepository.findById(id).get().getName());

        if (user != null) {//This is true with user
            if (userService.isUser()) {
                model.addAttribute("items", itemRepository.findAllByOccasion_IdAndUser(id, user));
            }
            if (userService.isAdmin()) {
                model.addAttribute("items", itemRepository.findAllByOccasion_Id(id));
            }
        } else {
            model.addAttribute("items", itemRepository.findAllByOccasion_Id(id));
        }
        return "detaillist";
    }

    @PostConstruct
    public void fillTables() {
       /* Occasion occasion = new Occasion();
        occasion.setTitle("Party");
        occasionRepository.save(occasion);

        occasion = new Occasion();
        occasion.setTitle("Meeting");
        occasionRepository.save(occasion);

        occasion = new Occasion();
        occasion.setTitle("Casual");
        occasionRepository.save(occasion);

        occasion = new Occasion();
        occasion.setTitle("Formal");
        occasionRepository.save(occasion);*/
    }
}
