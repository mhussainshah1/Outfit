package com.example.demo.web.controller;

import com.example.demo.business.entities.Category;
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

    public void findAll(Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("climates", climateRepository.findAll());
        model.addAttribute("occasions", occasionRepository.findAll());
        model.addAttribute("winds", windRepository.findAll());
    }

    @GetMapping("/addcategory")
    public String categoryForm(Model model){
        findAll(model);
        model.addAttribute("category", new Category());
        return "category";
    }

    @PostMapping("/processcategory")
    public String processSubject(@Valid Category category,
                                 BindingResult result,
                                 Model model){
        if(result.hasErrors()){
            for (ObjectError e : result.getAllErrors()){
                System.out.println(e);
            }
            findAll(model);
            return "category";
        }
        if(categoryRepository.findByName(category.getName()) != null){
            //String namedb = categoryRepository.findByName(category.getName()).getName();

            model.addAttribute("message", "You already have a category called " +
                    category.getName() + "!" + " Try something else.");
            findAll(model);
            return "category";
        }
        //category.setName(category.getName().toLowerCase());
        categoryRepository.save(category);
        return "redirect:/";
    }

    @RequestMapping("/detailcategory/{id}")
    public String showOutfitsByCategory(@PathVariable("id") long id, Model model){
        //This function is accesible without user
        if (userService.getUser() != null) {
            model.addAttribute("user_id", userService.getUser().getId());
        }
        findAll(model);
        model.addAttribute("items", itemRepository.findAllByCategory_Id(id));
        model.addAttribute("category", categoryRepository.findById(id).get());
        return "categorylist";
    }

    @PostConstruct
    public void fillTables(){
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
