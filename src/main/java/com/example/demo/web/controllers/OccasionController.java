package com.example.demo.web.controllers;

import com.example.demo.business.entities.Occasion;
import com.example.demo.business.entities.User;
import com.example.demo.business.entities.repositories.*;
import com.example.demo.business.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

        model.addAttribute("page_title", "Add Occasion");
        model.addAttribute("process", "processoccasion");
    }

    @GetMapping("/addoccasion")
    public String occasionForm(Model model) {
        findAll(model);
        model.addAttribute("object", new Occasion());
        return "type";
    }

    @PostMapping("/processoccasion")
    public String processSubject(@Valid @ModelAttribute("object") Occasion occasion,
                                 BindingResult result,
                                 Model model) {
        findAll(model);
        if (result.hasErrors()) {
            return "type";
        }
        occasion.setName(occasion.getName().toLowerCase());
        if (occasionRepository.findByName(occasion.getName()) != null) {
            model.addAttribute("message", "You already have a occasion called " +
                    occasion.getName() + "!" + " Try something else.");
            return "type";
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

    @RequestMapping("/deleteoccasion/{id}")
    public String deleteOccasion(@PathVariable("id") long id) {
        occasionRepository.deleteById(id);
        return "redirect:/";
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
