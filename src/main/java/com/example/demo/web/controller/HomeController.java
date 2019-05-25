package com.example.demo.web.controller;

import com.cloudinary.utils.ObjectUtils;
import com.example.demo.business.entities.Item;
import com.example.demo.business.entities.User;
import com.example.demo.business.entities.repositories.*;
import com.example.demo.business.services.FormAttributes;
import com.example.demo.business.services.UserService;
import com.example.demo.web.config.CloudinaryConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    OccasionRepository occasionRepository;

    @Autowired
    ClimateRepository climateRepository;

    @Autowired
    WindRepository windRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CloudinaryConfig cloudc;

    @Autowired
    UserService userService;

    public void findAll(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("climates", climateRepository.findAll());
        model.addAttribute("occasions", occasionRepository.findAll());
        model.addAttribute("winds", windRepository.findAll());
    }

    @RequestMapping("/")
    public String listItems(Principal principal, Model model) {
        findAll(model);
        User user = userService.getUser();
        System.out.println(user);
        /**
         * Alternative way to get user
         *-----------------------------
         *  User myuser = ((CustomerUserDetails)
         *                 ((UsernamePasswordAuthenticationToken) principal)
         *                         .getPrincipal())
         *                 .getUsers();
         */
        if (user != null) {
            model.addAttribute("formAttributes", new FormAttributes());
            if (userService.isUser()) {
                model.addAttribute("items", itemRepository.findAllByUser(user));
            }
            if (userService.isAdmin()) {
                model.addAttribute("items", itemRepository.findAll());
            }
        } else {
            model.addAttribute("items", itemRepository.findAll());
        }
        return "list";
    }

    @PostMapping("/search")
    public String searchword(Model model, @RequestParam String search) {
        findAll(model);
        User user = userService.getUser();
        Iterable<Item> results =
                itemRepository
                        .findAllByNameContainingOrDescriptionContainingAllIgnoreCase(search, search);
        if (user != null) {
            model.addAttribute("formAttributes", new FormAttributes());
            if (userService.isUser()) {
                model.addAttribute("items",
                        itemRepository
//                                .findAllByNameContainingOrDescriptionContainingAndUserAllIgnoreCase(search, search, user));
                                .findAllByUserAndNameContainingOrDescriptionContainingAllIgnoreCase(user,search,search));
            }
            if (userService.isAdmin()) {
                model.addAttribute("items", results);
            }
        } else {
            model.addAttribute("items", results);
        }
        return "list";
    }

    @RequestMapping("/admin")
    public String admin(Model model) {
        findAll(model);
        model.addAttribute("users", userRepository.findAll());
        return "admin";
    }

    @GetMapping("/add")
    public String itemForm(Model model) {
        findAll(model);
        model.addAttribute("imageLabel", "Upload Image");
        model.addAttribute("myuser", userService.getUser());
        model.addAttribute("item", new Item());
        return "itemform";
    }

    @PostMapping("/process")
    public String processForm(@Valid @ModelAttribute("item") Item item,
                              BindingResult result,
                              @RequestParam("file") MultipartFile file,
                              @RequestParam("hiddenImgURL") String ImgURL,
                              Model model) {
        findAll(model);
        model.addAttribute("imageLabel", "Upload Image");
        model.addAttribute("myuser", userService.getUser());
        if (result.hasErrors()) {
            return "itemform";
        }

        if (!file.isEmpty()) {
            try {
                Map uploadResult = cloudc.upload(file.getBytes(),
                        ObjectUtils.asMap("resourcetype", "auto"));
                item.setPicturePath(uploadResult.get("url").toString());

                String uploadedName = uploadResult.get("public_id").toString();
                String transformedImage = cloudc.createUrl(uploadedName, 150, 150);
                item.setPicturePath(transformedImage);

            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/add";
            }
        } else {
            if (!ImgURL.isEmpty()) {
                item.setPicturePath(ImgURL);
            } else {
                //item.setPicturePath("");
                return "itemform";
            }
        }
        item.setUser(userService.getUser());
        itemRepository.save(item); //generate SQL statement and insert into database
        return "redirect:/";
       /*
        //if there is a picture path and file is empty then save message
        if (item.getPicturePath() != null && file.isEmpty()) {
            itemRepository.save(item);
            return "redirect:/";
        }

        if (file.isEmpty()) {
            model.addAttribute("myuser", userService.getUser());
            return "itemform";
        }
        Map uploadResult;
        try {
            uploadResult = cloudc.upload(
                    file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
        } catch (IOException e) {
            e.printStackTrace();
            findAll(model);
            model.addAttribute("myuser", userService.getUser());
            return "redirect:/itemform";
        }
        String url = uploadResult.get("url").toString();
        String uploadedName = uploadResult.get("public_id").toString();
        String transformedImage = cloudc.createUrl(uploadedName, 150, 150);

        item.setPicturePath(transformedImage);
        item.setUser(userService.getUser());
        itemRepository.save(item); //generate SQL statement and insert into database
        return "redirect:/";*/
    }

    @RequestMapping("/detail/{id}")
    public String showItem(@PathVariable("id") long id, Model model) {
        findAll(model);
        model.addAttribute("item", itemRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateItem(@PathVariable("id") long id,
                             @ModelAttribute Item item,
                             Model model) {
        findAll(model);
        item = itemRepository.findById(id).get();
        model.addAttribute("myuser", userService.getUser());
        model.addAttribute("item", itemRepository.findById(id).get());
        model.addAttribute("imageURL", item.getPicturePath());

        if (item.getPicturePath().isEmpty()) {
            model.addAttribute("imageLabel", "Upload Image");
        } else {
            model.addAttribute("imageLabel", "Upload New Image");
        }
        return "itemform";
    }

    @RequestMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") long id) {
        itemRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/check")
    public String check(@RequestParam("check") long[] ids,
                        @RequestParam("name") String name,
                        Model model) {
        if (name.equals("delete")) {
            for (long id : ids) {
                itemRepository.deleteById(id);
            }
            return "redirect:/";
        }

        if (name.equals("packing")) {
            Set<Item> items = new HashSet<>();
            for (long id : ids) {
                items.add(itemRepository.findById(id).get());
                System.out.println(id);
            }
            model.addAttribute("page_title", "Packing List");
            model.addAttribute("items", items);
            return "detaillist";
        }
        return "list";
    }

    @PostMapping("/delete")
    public String deleteBooks(@RequestParam("check") long[] ids) {
        for (long id : ids) {
            itemRepository.deleteById(id);
        }
        return "redirect:/";
    }


    @PostMapping("/packinglist")
    public String getPackingList(@RequestParam("check") long[] ids,
                                 Model model) {
        findAll(model);
        Set<Item> items = new HashSet<>();
        for (long id : ids) {
            items.add(itemRepository.findById(id).get());
            System.out.println(id);
        }
        model.addAttribute("page_title", "Packing List");
        model.addAttribute("items", items);
        return "detaillist";
    }


    @GetMapping("/about")
    public String getAbout(Model model) {
        findAll(model);
        return "about";
    }
}
