package com.example.demo.web.controller;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.business.entities.Category;
import com.example.demo.business.entities.Item;
import com.example.demo.business.entities.User;
import com.example.demo.business.entities.repositories.*;
import com.example.demo.web.config.CloudinaryConfig;
import com.example.demo.business.services.CustomerUserDetails;
import com.example.demo.business.services.UserService;
import com.example.demo.business.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@Controller
public class HomeController {

    //
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ItemRepository itemRepository;


    @Autowired
    OccasionRepository occasionRepository;

    @Autowired
    ClimateRepository climateRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CloudinaryConfig cloudc;

    @Autowired
    UserService userService;

    public void findAll(Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("climates", climateRepository.findAll());
        model.addAttribute("occasions", occasionRepository.findAll());
    }

    @RequestMapping("/")
    public String listItems(Principal principal, Model model) {
        model.addAttribute("items", itemRepository.findAll()); //generate select * statement
        findAll(model);
        if (userService.getUser() != null) {
            model.addAttribute("user_id", userService.getUser().getId());
        }
        return "list";
    }

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
    //Use the below code INSIDE METHOD to pass user into the view
    @RequestMapping("/secure")
    public String secure(Principal principal, Model model) {
        User myuser = ((CustomerUserDetails)
                ((UsernamePasswordAuthenticationToken) principal)
                        .getPrincipal())
                .getUser();
        model.addAttribute("myuser", myuser);
        return "secure";
    }

    @GetMapping("/add")
    public String itemForm(Model model) {
        findAll(model);
        model.addAttribute("myuser", userService.getUser());
        model.addAttribute("item", new Item());
        return "itemform";
    }

    @PostMapping("/process")
    public String processForm(@Valid @ModelAttribute("item") Item item,
                              BindingResult result,
                              @RequestParam("file") MultipartFile file) {
        if (result.hasErrors()) {
            for (ObjectError e : result.getAllErrors()) {
                System.out.println(e);
            }
            return "itemform";
        }

        //if there is a picture path and file is empty then save message
        if (item.getPicturePath() != null && file.isEmpty()) {
            itemRepository.save(item);
            return "redirect:/";
        }

        if (file.isEmpty()) {
            return "itemform";
        }
        Map uploadResult;
        try {
            uploadResult = cloudc.upload(
                    file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/itemform";
        }
        String url = uploadResult.get("url").toString();
        String uploadedName = uploadResult.get("public_id").toString();
        String transformedImage = cloudc.createUrl(uploadedName,150,150);

        item.setPicturePath(transformedImage);
        item.setUser(userService.getUser());
        itemRepository.save(item); //generate SQL statement and insert into database
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showItem(@PathVariable("id") long id, Model model) {
        findAll(model);
        model.addAttribute("item", itemRepository.findById(id).get());
        if (userService.getUser() != null) {
            model.addAttribute("user_id", userService.getUser().getId());
        }
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateItem(@PathVariable("id") long id, Model model) {
        findAll(model);
        model.addAttribute("myuser", userService.getUser());
        model.addAttribute("item", itemRepository.findById(id).get());
        return "itemform";
    }

    @RequestMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") long id) {
        itemRepository.deleteById(id);
        return "redirect:/";
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

    @GetMapping("/about")
    public String getAbout(Model model) {
        findAll(model);
        return "about";
    }
}
