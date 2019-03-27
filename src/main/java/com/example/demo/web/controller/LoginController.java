package com.example.demo.web.controller;

import com.example.demo.business.entities.User;
import com.example.demo.business.entities.repositories.UserRepository;
import com.example.demo.business.services.UserService;
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
    UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/forgot-password")
    public String forgetPassword() {
        return "/";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/termsandconditions")
    public String getTermsAndCondition() {
        return "termsandconditions";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, @RequestParam("password") String pw) {
        System.out.println("pw: " + pw);
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        } else {
            user.setPassword(userService.encode(pw));
            userService.saveUser(user);

            boolean isUser = userRepository.findById(user.getId()).isPresent();
           /* if (isUser) {//For Update Registration
                Iterable<Pet> pets = petRepository.findAllByUsers(user);
                for (Pet pet : pets) {
                    petRepository.save(pet);
                    user.getPets().add(pet);
                }
            }*/
            if (userService.isUser()) {
                user.setPassword(userService.encode(pw));
                userService.saveUser(user);
            }
            if (userService.isAdmin()) {
                user.setPassword(userService.encode(pw));
                userService.saveAdmin(user);
            }
            model.addAttribute("message", "User Account Successfully Created");
        }
        return "login";
    }

    @RequestMapping("/updateUser")
    public String viewUser(Model model,
                           HttpServletRequest request,
                           Authentication authentication,
                           Principal principal) {
       /* Boolean isAdmin = request.isUserInRole("ADMIN");
        Boolean isUser = request.isUserInRole("USER");
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();*/
        String username = principal.getName();
        model.addAttribute("user", userRepository.findByUsername(username));
        return "register";
    }
}
