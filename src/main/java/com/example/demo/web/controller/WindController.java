package com.example.demo.web.controller;

import com.example.demo.business.entities.Wind;
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
public class WindController {

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

    @GetMapping("/addwind")
    public String windForm(Model model){
        findAll(model);
        model.addAttribute("wind", new Wind());
        return "wind";
    }

    @PostMapping("/processwind")
    public String processSubject(@Valid Wind wind,
                                 BindingResult result,
                                 Model model){
        if(result.hasErrors()){
            for (ObjectError e : result.getAllErrors()){
                System.out.println(e);
            }
            findAll(model);
            return "wind";
        }
        if(windRepository.findByName(wind.getName()) != null){
            model.addAttribute("message", "You already have a wind called " +
                    wind.getName() + "!" + " Try something else.");
            return "wind";
        }
        windRepository.save(wind);
        return "redirect:/";
    }

    @RequestMapping("/detailwind/{id}")
    public String showItemsByWind(@PathVariable("id") long id, Model model){
        //This function is accesible without user
        if (userService.getUser() != null) {
            model.addAttribute("user_id", userService.getUser().getId());
        }
        findAll(model);
        model.addAttribute("items", itemRepository.findAllByWind_Id(id));
        model.addAttribute("wind", windRepository.findById(id).get());
        return "windlist";
    }

    @PostConstruct
    public void fillTables(){
       /* Wind wind = new Wind();
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
