package com.outfit.business.web.controllers;

import com.outfit.business.services.EmailService;
import com.outfit.business.services.UserService;
import com.outfit.business.util.MD5Util;
import com.outfit.persistence.dao.*;
import com.outfit.persistence.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class LoginController {
    BCryptPasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;
    private ItemRepository itemRepository;
    private ClimateRepository climateRepository;
    private OccasionRepository occasionRepository;
    private WindRepository windRepository;
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @Autowired
    public LoginController(UserRepository userRepository, CategoryRepository categoryRepository, ItemRepository itemRepository, ClimateRepository climateRepository, OccasionRepository occasionRepository, WindRepository windRepository, UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
        this.climateRepository = climateRepository;
        this.occasionRepository = occasionRepository;
        this.windRepository = windRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

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

    @GetMapping("/forgot-password")
    public String forgetPassword() {
        return "/";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        findAll(model);
        model.addAttribute("page_title", "New User Registration");
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user,
                                          //@ModelAttribute("user") is not necessary here.
                                          // We always use if the name of object on form is different from Class
                                          //e.g. if the User class object on form is user1 instead of user then
                                          // we have to use this annotation
                                          BindingResult result,
                                          Model model/*,
                                          @RequestParam("password") String password*/) {
        findAll(model);
        model.addAttribute("page_title", "Update Profile");
        if (result.hasErrors()) {
            return "register";
        } else {
            //Update User and Admin
            var isUser = userRepository.existsById(user.getId());
            if (isUser) {
                //updating with existed username
                if (userRepository.findByUsername(user.getUsername()) != null &&
                        //current user
                        !userRepository.findByUsername(user.getUsername()).equals(user)) {
                    model.addAttribute("message",
                            "We already have a username called " + user.getUsername() + "!" + " Try something else.");
                    return "register";
                }

                var userInDB = userRepository.findById(user.getId()).get();
                userInDB.setFirstName(user.getFirstName());
                userInDB.setLastName(user.getLastName());
                userInDB.setEmail(user.getEmail());
                userInDB.setUsername(user.getUsername());
                userInDB.setPassword(passwordEncoder.encode(user.getPassword()));
                userInDB.setEnabled(user.isEnabled());
                userRepository.save(userInDB);
                model.addAttribute("message", "User Account Successfully Updated");
            }
            //New User
            else {
                //Registering with existed username
                if (userRepository.findByUsername(user.getUsername()) != null) {
                    model.addAttribute("message",
                            "We already have a username called " + user.getUsername() + "!" + " Try something else.");
                    return "register";
                } else {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    userService.saveUser(user);
                    model.addAttribute("message", "User Account Successfully Created");
                    String subject = "Welcome to our email list";
                    String body = """
                            Dear $firstName ,
                                                        
                            Thanks for registring our web site. We'll make sure to send you announcements about new products and promotions.
                            Have a great day and thanks again!
                                                        
                                                        
                            Muhammad Shah
                            Java Certified Professional
                            """.replace("$firstName",user.getFirstName());

                    emailService.sendSimpleEmail(user.getEmail(),subject, body);
                }
            }
        }
        return "redirect:/login";
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
        model.addAttribute("page_title", "Update Profile");
        model.addAttribute("user", userService.getUser());
        return "register";
    }
}