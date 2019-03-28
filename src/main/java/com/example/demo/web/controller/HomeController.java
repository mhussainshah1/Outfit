package com.example.demo.web.controller;

import com.example.demo.business.entities.Category;
import com.example.demo.business.entities.Temperature;
import com.example.demo.business.entities.User;
import com.example.demo.business.entities.repositories.*;
import com.example.demo.business.services.CustomerUserDetails;
import com.example.demo.business.services.UserService;
import com.example.demo.business.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class HomeController {

    /********************** INSTANTIATED REPO'S BY ME ********************************************** */
    //
    @Autowired
    CategoryRepository categoryRepository;


    @Autowired
    ItemRepository itemRepository;

    @Autowired
    TemperatureRepository temperatureRepository;

    /***********************        END       ******************************************/

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    /************************     MAPPING FOR CATEGORY BY ME   ***************************************/


    @GetMapping("/addCategory")
    public String newCategory(Model model) {
        model.addAttribute("type", new Category());
        return "category";
    }

    @PostMapping("/addCategory")
    public String processCategory(@Valid @ModelAttribute("category") Category category) {
        categoryRepository.save(category);
        return "redirect:/";
    }

    @RequestMapping("/")
    public String listItems(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "list";
    }


    //FOR TEMPERATURE
    @GetMapping("/addTemp")
    public String newType(Model model){
        model.addAttribute("temp",new Temperature());
        return "temperature";
    }


    @PostMapping("/addTemp")
    public String processCategory(@Valid @ModelAttribute("temperature") Temperature temperature){
        temperatureRepository.save(temperature);
        return "redirect:/";
    }


    /************************   END CATEGORY MAPPING     *******************************/

//    @RequestMapping("/")
//    public String listCourses(Model model) {
//        model.addAttribute("courses", courseRepository.findAll()); //generate select * statement
//        if (userService.getUser() != null) {
//            model.addAttribute("user_id", userService.getUser().getId());
//        }
//        return "list";
//    }

    //Users with Admin role can view this page
    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }

    //AUXILLARY FUNCTION!!!
    //Use the below code INSIDE METHOD to pass user into view
    @RequestMapping("/secure")
    public String secure(Principal principal, Model model) {
        User myuser = ((CustomerUserDetails)
                ((UsernamePasswordAuthenticationToken) principal)
                        .getPrincipal())
                .getUser();
        model.addAttribute("myuser", myuser);
        return "secure";
    }


//    @PostMapping("/process")
//    public String processForm(@Valid Course course, BindingResult result) {
//        if (result.hasErrors()) {
//            return "courseform";
//        }
//
//        course.setUser(userService.getUser());
//        courseRepository.save(course); //generate SQL statement and insert into database
//        return "redirect:/";
//    }

    @RequestMapping("/detail/{id}")
    public String showItem(@PathVariable("id") long id, Model model) {
        model.addAttribute("item", itemRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model) {
        model.addAttribute("course", courseRepository.findById(id).get());
        return "courseform";
    }

    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") long id) {
        courseRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/profile")
    public String getProfile(Principal principal, Model model) {
        if (userService.getUser() != null) {
            model.addAttribute("user", userService.getUser());
            model.addAttribute("HASH", MD5Util.md5Hex(userService.getUser().getEmail()));
        }
        return "profile";
    }

    @GetMapping("/about")
    public String getAbout() {
        return "about";
    }
}
