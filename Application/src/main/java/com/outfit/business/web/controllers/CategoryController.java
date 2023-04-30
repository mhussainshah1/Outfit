package com.outfit.business.web.controllers;

import com.outfit.persistence.model.Category;
import com.outfit.business.services.UserService;
import com.outfit.persistence.dao.*;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoryController {
    private ItemRepository itemRepository;
    private CategoryRepository categoryRepository;
    private ClimateRepository climateRepository;
    private OccasionRepository occasionRepository;
    private WindRepository windRepository;
    private UserService userService;
    @Autowired
    public CategoryController(ItemRepository itemRepository, CategoryRepository categoryRepository, ClimateRepository climateRepository, OccasionRepository occasionRepository, WindRepository windRepository, UserService userService) {
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

        model.addAttribute("page_title", "Add Category");
        model.addAttribute("process", "processcategory");
    }

    @GetMapping("/addcategory")
    public String categoryForm(Model model) {
        findAll(model);
        model.addAttribute("object", new Category());
        return "type";
    }

    @PostMapping("/processcategory")
    public String processCategory(@Valid @ModelAttribute("object") Category category,
                                  BindingResult result,
                                  Model model) {
        findAll(model);
        if (result.hasErrors()) {
            return "type";
        }
        boolean isPresent = categoryRepository.existsById(category.getId());
        if (isPresent) {
            var categoryDB = categoryRepository.findById(category.getId()).get();
            categoryDB.setName(category.getName().toLowerCase());
            categoryRepository.save(categoryDB);
            model.addAttribute("message", "Category Successfully Updated");
        } else {
            category.setName(category.getName().toLowerCase());
            if (categoryRepository.findByName(category.getName()) != null) {
                model.addAttribute("message", "You already have a category called " +
                        category.getName() + "!" + " Try something else.");
                return "type";
            }
            categoryRepository.save(category);
        }
        return "redirect:/";
    }

    @RequestMapping("/detailcategory/{id}")
    public String showOutfitsByCategory(@PathVariable("id") long id, Model model) {
        findAll(model);
        var user = userService.getUser();
        model.addAttribute("page_title", categoryRepository.findById(id).get().getName());

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
        return "detaillist";
    }

    @RequestMapping("/deletecategory/{id}")
    public String deleteCategory(@PathVariable("id") long id) {
        categoryRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/updatecategory/{id}")
    public String updateItem(@PathVariable("id") long id, Model model) {
        findAll(model);
        model.addAttribute("page_title", "Update Category");
        model.addAttribute("object", categoryRepository.findById(id).get());
        return "type";
    }

    @PostConstruct
    public void fillTables() {
       /* var category = new Category();
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
