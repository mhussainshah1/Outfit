package com.example.demo.web.controller;

import com.example.demo.business.entities.Category;
import com.example.demo.business.entities.Occasion;
import com.example.demo.business.entities.repositories.CategoryRepository;
import com.example.demo.business.entities.repositories.ClimateRepository;
import com.example.demo.business.entities.repositories.ItemRepository;
import com.example.demo.business.entities.repositories.OccasionRepository;
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
    OccasionRepository occasionRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ClimateRepository climateRepository;

    public void findAll(Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("climates", climateRepository.findAll());
        model.addAttribute("occasions", occasionRepository.findAll());
    }

    @GetMapping("/addoccasion")
    public String occasionForm(Model model){
        findAll(model);
        model.addAttribute("occasion", new Occasion());
        return "occasion";
    }

    @PostMapping("/processoccasion")
    public String processSubject(@Valid Occasion occasion,
                                 BindingResult result,
                                 Model model){
        if(result.hasErrors()){
            for (ObjectError e : result.getAllErrors()){
                System.out.println(e);
            }
            return "occasion";
        }
        if(occasionRepository.findByName(occasion.getName()) != null){
            model.addAttribute("message", "You already have a occasion called " +
                    occasion.getName() + "!" + " Try something else.");
            return "occasion";
        }
        occasionRepository.save(occasion);
        return "redirect:/";
    }

    @RequestMapping("/detailoccasion/{id}")
    public String showCarsByOccasion(@PathVariable("id") long id, Model model){
        findAll(model);
        model.addAttribute("cars", itemRepository.findAllByOccasion_Id(id));
        model.addAttribute("occasion", occasionRepository.findById(id).get());
        return "occasionlist";
    }

    @PostConstruct
    public void fillTables(){
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
