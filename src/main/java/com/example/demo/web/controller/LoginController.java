package com.example.demo.web.controller;

import com.example.demo.business.entities.Item;
import com.example.demo.business.entities.User;
import com.example.demo.business.entities.repositories.*;
import com.example.demo.business.services.UserService;
import com.example.demo.business.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ItemRepository itemRepository;

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

    @RequestMapping("/login")
    public String login(Model model) {
        findAll(model);
        return "login";
    }

    @RequestMapping("/profile")
    public String getProfile(Principal principal, Model model) {
        findAll(model);
        if (userService.getUser() != null) {
            model.addAttribute("user", userService.getUser());
            model.addAttribute("HASH", MD5Util.md5Hex(userService.getUser().getEmail()));
        }
        return "profile";
    }

    @PostMapping("/forgot-password")
    public String forgetPassword() {
        return "/";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        findAll(model);
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user,
                                          BindingResult result,
                                          Model model,
                                          @RequestParam("password") String pw) {
        System.out.println("password: " + pw);

        if (result.hasErrors()) {
            findAll(model);
            return "register";
        } else {
            System.out.println(user);
            //Update User and Admin
            boolean isUser = userRepository.findById(user.getId()).isPresent();
            if(isUser){

                Iterable<Item> items = itemRepository.findAllByUser(user);
                for (Item item : items) {
                    itemRepository.save(item);
                    user.getItems().add(item);
                }
                if (userService.isUser()) {
                    user.setPassword(userService.encode(pw));
                    userService.saveUser(user);
                }
                if (userService.isAdmin()) {
                    user.setPassword(userService.encode(pw));
                    userService.saveAdmin(user);
                }
                model.addAttribute("message", "User Account Successfully Updated");
            }
            //New User
            else {
                //Registering with existed username
//                todo: inserting existed username in update profile
                if (userRepository.findByUsername(user.getUsername())!= null && user.getId() == 0) {
                    model.addAttribute("message", "We already have a username called " +
                            user.getUsername() + "!" + " Try something else.");
                    findAll(model);
                    return "register";
                } else {
                    user.setPassword(userService.encode(pw));
                    userService.saveUser(user);
                    model.addAttribute("message", "User Account Successfully Created");
                }
            }
        }
        return "login";
    }

    @GetMapping("/termsandconditions")
    public String getTermsAndCondition(Model model) {
        findAll(model);
        return "termsandconditions";
    }


    @RequestMapping("/updateUser")
    public String viewUser(Model model,
                           HttpServletRequest request,
                           Authentication authentication,
                           Principal principal) {
       /* Boolean isAdmin = request.isUserInRole("ADMIN");
        Boolean isUser = request.isUserInRole("USER");
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();*/
//        String username = principal.getName();
        model.addAttribute("user", userService.getUser());
        return "register";
    }


}
