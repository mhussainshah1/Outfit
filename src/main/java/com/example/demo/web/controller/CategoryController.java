package com.example.demo.web.controller;

import com.example.demo.business.entities.Category;
import com.example.demo.business.entities.User;
import com.example.demo.business.entities.repositories.*;
import com.example.demo.business.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@Controller
public class CategoryController {

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

    @GetMapping("/addcategory")
    public String categoryForm(Model model) {
        findAll(model);
        model.addAttribute("category", new Category());
        return "category";
    }

    @PostMapping("/processcategory")
    public String processSubject(@Valid Category category,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            findAll(model);
            return "category";
        }
        if (categoryRepository.findByName(category.getName()) != null) {
            model.addAttribute("message", "You already have a category called " +
                    category.getName() + "!" + " Try something else.");
            findAll(model);
            return "category";
        }
        categoryRepository.save(category);
        return "redirect:/";
    }

    @RequestMapping("/detailcategory/{id}")
    public String showOutfitsByCategory(@PathVariable("id") long id, Model model) {
        findAll(model);
        User user = userService.getUser();
        model.addAttribute("category", categoryRepository.findById(id).get());

        if (user != null) {//This is true with user
            if (userService.isUser()) {
                model.addAttribute("items", itemRepository.findAllByCategory_IdAndUser(id, user));
            }
            if (userService.isAdmin()) {
                model.addAttribute("items", itemRepository.findAllByCategory_Id(id));
            }
        } else {
            model.addAttribute("items", itemRepository.findAllByCategory_Id(id));
        }
        return "categorylist";
    }

    @PostConstruct
    public void fillTables() {
       /* Category category = new Category();
        category.setTitle("Compact");
        categoryRepository.save(category);

        category = new Category();
        category.setTitle("Medium Size");
        categoryRepository.save(category);

        category = new Category();
        category.setTitle("Full Size");
        categoryRepository.save(category);

        category = new Category();
        category.setTitle("Luxury");
        categoryRepository.save(category);*/
    }
}
