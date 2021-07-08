package com.outfit.business.services;

import com.example.demo.business.entities.*;
import com.example.demo.business.entities.repositories.*;
import com.outfit.business.entities.*;
import com.outfit.business.entities.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

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
    InvalidPasswordRepository invalidPasswordRepository;

    @Autowired
    UserService userService;

    @Value("${run.dataloader}")
    private boolean rundataloader;

    @Override
    public void run(String... args) throws Exception {

        if (rundataloader) {
            //if you want to run dataloader once then change run.dataloader = true in application.properties.
            // it will help you to not comment out whole class

            //Password
            var passwords = new HashSet<InvalidPassword>();
            passwords.add(new InvalidPassword("azerty12!"));
            passwords.add(new InvalidPassword("12345678!"));
            passwords.add(new InvalidPassword("password123"));
            invalidPasswordRepository.saveAll(passwords);

            //Role
            roleRepository.save(new Role("USER"));
            roleRepository.save(new Role("ADMIN"));

            var userRole = roleRepository.findByRole("USER");
            var adminRole = roleRepository.findByRole("ADMIN");

            //User
            var admin = new User("admin@admin.com", userService.encode("password"), "Admin", "User", true, "admin");
            userService.saveAdmin(admin);

            var moe = new User("mhussainshah79@gmail.com", userService.encode("password"), "Muhammad", "Shah", true, "moe");
            userService.saveUser(moe);

            var lan = new User("tolani.oyefule@gmail.com", userService.encode("password"), "Tolani", "Oyefule", true, "lan");
            userService.saveUser(lan);

            var nan = new User("nhan.cog.huynh@gmail.com", userService.encode("password"), "Nhan", "Huynh", true, "nan");
            userService.saveUser(nan);

            var dag = new User("dag@gmail.com", userService.encode("password"), "Dag", "Fasil", true, "dag");
            userService.saveUser(dag);

            var mel = new User("melissafong@gmail.com", userService.encode("password"), "Mellisa", "Lavander", true, "mel");
            userService.saveUser(mel);

            var jen = new User("jen@gmail.com", userService.encode("password"), "Jennifer", "You", true, "jen");
            userService.saveUser(jen);

            //Category
            categoryRepository.save(new Category("top"));
            var top = categoryRepository.findByName("top");

            categoryRepository.save(new Category("glasses"));
            var glasses = categoryRepository.findByName("glasses");

            categoryRepository.save(new Category("jacket"));
            var jacket = categoryRepository.findByName("jacket");

            categoryRepository.save(new Category("bottom"));
            var bottom = categoryRepository.findByName("bottom");

            categoryRepository.save(new Category("socks"));
            var socks = categoryRepository.findByName("socks");

            categoryRepository.save(new Category("shoes"));
            var shoe = categoryRepository.findByName("shoes");

            categoryRepository.save(new Category("accessories"));
            var accessories = categoryRepository.findByName("accessories");

            //Climate
            climateRepository.save(new Climate("cold"));
            var cold = climateRepository.findByName("cold");

            climateRepository.save(new Climate("moderate"));
            var mild = climateRepository.findByName("moderate");

            climateRepository.save(new Climate("hot"));
            var hot = climateRepository.findByName("hot");

            climateRepository.save(new Climate("rainy"));
            var rainy = climateRepository.findByName("rainy");

            //Occasion
            occasionRepository.save(new Occasion("active"));
            var active = occasionRepository.findByName("active");

            occasionRepository.save(new Occasion("party"));
            var party = occasionRepository.findByName("party");

            occasionRepository.save(new Occasion("nightlife"));
            var nightlife = occasionRepository.findByName("nightlife");

            occasionRepository.save(new Occasion("meeting"));
            var meeting = occasionRepository.findByName("meeting");

            occasionRepository.save(new Occasion("casual"));
            var casual = occasionRepository.findByName("casual");

            occasionRepository.save(new Occasion("formal"));
            var formal = occasionRepository.findByName("formal");

            occasionRepository.save(new Occasion("dinner"));
            var dinner = occasionRepository.findByName("dinner");

            //Wind
            windRepository.save(new Wind("light"));
            var light = windRepository.findByName("light");

            windRepository.save(new Wind("moderate"));
            var moderate = windRepository.findByName("moderate");

            windRepository.save(new Wind("high"));
            var high = windRepository.findByName("high");

            windRepository.save(new Wind("periodic"));
            var periodic = windRepository.findByName("periodic");

            //moe items
            itemRepository.save(new Item("homeboy shirt",
                    "grey",
                    "cotton",
                    "small",
                    "https://res.cloudinary.com/toyefule/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553799014/Outfit/JESUS_IS_MY_HOMEBOY_heathergrey_2000x.jpg",
                    "men's wear",
                    moe,
                    top,
                    cold,
                    casual,
                    light));
            var Homeboyshirt = itemRepository.findByName("homeboy shirt");

            itemRepository.save(new Item("cargo pants",
                    "khaki",
                    "polyester",
                    "medium",
                    "https://res.cloudinary.com/toyefule/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553802838/Outfit/Cargo_khaki_1.jpg",
                    "men's wear",
                    moe,
                    bottom,
                    cold,
                    casual,
                    moderate));
            var pant = itemRepository.findByName("cargo pant");

            itemRepository.save(
                    new Item("light jacket",
                            "white",
                            "leather",
                            "large",
                            "https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553756501/outfit/Jackets/Warm/Black_Nike_WIndbreaker.jpg",
                            "men's wear",
                            moe,
                            jacket,
                            mild,
                            casual,
                            moderate));
            var lightJacket = itemRepository.findByName("light jacket");

            itemRepository.save(new Item("socks",
                    "grey",
                    "cotton",
                    "US 10",
                    "https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553756502/outfit/Shoes/Warm/Warm_Midhigh_Socks.jpg",
                    "men's wear",
                    moe,
                    shoe,
                    hot,
                    casual,
                    light));
            var sandle = itemRepository.findByName("socks");

            itemRepository.save(
                    new Item("ball shorts white",
                            "White",
                            "Cotton",
                            "large",
                            "https://res.cloudinary.com/toyefule/image/upload/c_fill,g_face,h_150,r_50,w_150/v1554130657/Outfit/Bottoms/Shorts_Ball_Cool_White.jpg",
                            "men's wear",
                            moe,
                            bottom,
                            mild,
                            active,
                            light));
            var sneakers = itemRepository.findByName("ball shorts white");

            //tolani item
            itemRepository.save(new Item("long sleeve shirt",
                    "grey",
                    "cotton",
                    "small",
                    "https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553756502/outfit/Tops/Cold/Grey_Long_Sleeve.jpg",
                    "men's wear",
                    lan,
                    top,
                    cold,
                    dinner,
                    high));
            var shirt = itemRepository.findByName("long sleeve shirt");

            itemRepository.save(new Item("jeans",
                    "blue",
                    "cotton",
                    "medium",
                    "https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553756501/outfit/Bottom/Cold/Jeans_Blue_1.jpg",
                    "men's wear",
                    lan,
                    bottom,
                    cold,
                    casual,
                    high));
            var jeans = itemRepository.findByName("jeans");

            itemRepository.save(
                    new Item("white & blue windbreaker jacket",
                            "white",
                            "nylon",
                            "large",
                            "https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553756501/outfit/Jackets/Warm/Black_Nike_WIndbreaker.jpg",
                            "men's wear",
                            lan,
                            jacket,
                            mild,
                            casual,
                            moderate));
            var bluewhiteJacket = itemRepository.findByName("white & blue windbreaker jacket");

            itemRepository.save(
                    new Item("nike sneakers",
                            "Black and Green",
                            "leather",
                            "US 10",
                            "https://res.cloudinary.com/toyefule/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553797544/Outfit/Nike-Outdoor-Green-Court-Borough-Mid-Winter-Shoes.jpg",
                            "men's wear",
                            lan,
                            shoe,
                            hot,
                            casual,
                            moderate));
            sneakers = itemRepository.findByName("nike sneakers");

            itemRepository.save(
                    new Item("timberland boots",
                            "Black and Tan",
                            "leather",
                            "US 12",
                            "https://res.cloudinary.com/toyefule/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553797537/Outfit/Black_Tan_Timberland.jpg",
                            "men's wear",
                            lan,
                            shoe,
                            cold,
                            casual,
                            moderate));
            sneakers = itemRepository.findByName("timberland boots");

            itemRepository.save(
                    new Item("ball shorts black",
                            "BLack",
                            "Cotton",
                            "large",
                            "https://res.cloudinary.com/toyefule/image/upload/c_fill,g_face,h_150,r_50,w_150/v1554130657/Outfit/Bottoms/Shorts_Ball_Cool_Obsidian_Black.jpg",
                            "men's wear",
                            lan,
                            bottom,
                            mild,
                            active,
                            light));
            sneakers = itemRepository.findByName("ball shorts black");

            //nan item
            itemRepository.save(
                    new Item("short sleeve Shirt",
                            "BluePi",
                            "cotton",
                            "Medium",
                            "https://res.cloudinary.com/toyefule/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553797471/Outfit/Blue_shortsleeve_Science_Shirt.jpg",
                            "men's wear",
                            nan,
                            top,
                            cold,
                            dinner,
                            light));
            var pishirt = itemRepository.findByName("short sleeve Shirt");

            itemRepository.save(
                    new Item("green & grey wind breaker jacket",
                            "Green Grey",
                            "nylon",
                            "large",
                            "https://res.cloudinary.com/toyefule/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553797638/Outfit/Green_Grey_1.jpg",
                            "men's wear",
                            nan,
                            jacket,
                            mild,
                            casual,
                            high));
            var greengreyJacket = itemRepository.findByName("green & grey wind breaker jacket");

            itemRepository.save(
                    new Item("ball shorts grey",
                            "Grey",
                            "Cotton",
                            "large",
                            "https://res.cloudinary.com/toyefule/image/upload/c_fill,g_face,h_150,r_50,w_150/v1554130657/Outfit/Bottoms/Shorts_Ball_Cool_Grey.jpg",
                            "men's wear",
                            nan,
                            bottom,
                            mild,
                            active,
                            light));
            sneakers = itemRepository.findByName("ball shorts grey");

            itemRepository.save(
                    new Item("khaki shorts",
                            "Tan",
                            "cotton",
                            "medium",
                            "https://res.cloudinary.com/toyefule/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553797908/Outfit/Shorts_Khaki_1.jpg",
                            "men's wear",
                            nan,
                            bottom,
                            cold,
                            casual,
                            light));
            var khakishorts = itemRepository.findByName("khaki shorts");

            itemRepository.save(
                    new Item("all weather sandles",
                            "brown and Green",
                            "leather",
                            "US 10",
                            "https://res.cloudinary.com/toyefule/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553797718/Outfit/Nhan_All-weather_Sandals.jpg",
                            "men's wear",
                            nan,
                            shoe,
                            cold,
                            casual,
                            light));
            var sandals = itemRepository.findByName("all weather sandles");

            //dag item
            itemRepository.save(new Item("grey sperry loafer",
                    "Grey",
                    "leather",
                    "10",
                    "https://res.cloudinary.com/toyefule/image/upload/c_fill,g_face,h_150,r_50,w_150/v1554132719/Outfit/Shoes/Grey_Sperry_Loafer.jpg",
                    "men's wear",
                    dag,
                    shoe,
                    cold,
                    casual,
                    moderate));
            shirt = itemRepository.findByName("grey sperry loafer"); //

            itemRepository.save(
                    new Item("khaki brown pants",
                            "brown",
                            "cotton",
                            "medium",
                            "https://res.cloudinary.com/toyefule/image/upload/c_fill,g_face,h_150,r_50,w_150/v1554134318/Outfit/Bottom/dag_flat_chino.jpg",
                            "men's wear",
                            dag,
                            bottom,
                            cold,
                            casual,
                            moderate));
            pant = itemRepository.findByName("khaki brown pants");

            itemRepository.save(
                    new Item("winter hoodie vest",
                            "Black",
                            "cotton",
                            "large",
                            "https://res.cloudinary.com/toyefule/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553797632/Outfit/Black_Light-Grey_2.jpg",
                            "men's wear",
                            dag,
                            jacket,
                            cold,
                            casual,
                            high));
            lightJacket = itemRepository.findByName("winter hoodie vest");

            itemRepository.save(
                    new Item("ball shorts blue",
                            "Blue",
                            "Cotton",
                            "large",
                            "https://res.cloudinary.com/toyefule/image/upload/c_fill,g_face,h_150,r_50,w_150/v1554130657/Outfit/Bottoms/Shorts_Ball_Cool_Blue.jpg",
                            "men's wear",
                            dag,
                            bottom,
                            mild,
                            active,
                            light));
            sneakers = itemRepository.findByName("ball shorts blue");

            //mel item
            itemRepository.save(new Item("cream scarf",
                    "cream ",
                    "cotton",
                    "L",
                    "https://res.cloudinary.com/toyefule/image/upload/c_fill,g_face,h_150,r_50,w_150/v1554135255/Outfit/Accessories/Cream_Scarf.jpg",
                    "women's wear",
                    mel,
                    accessories,
                    cold,
                    casual, moderate));
            var cream_scarf = itemRepository.findByName("cream scarf");

            itemRepository.save(new Item("red scarf",
                    "red",
                    "cotton",
                    "L",
                    "https://res.cloudinary.com/toyefule/image/upload/c_fill,g_face,h_150,r_50,w_150/v1554135264/Outfit/Accessories/Red_Scarf.jpg",
                    "women's wear",
                    mel,
                    accessories,
                    cold,
                    casual,
                    moderate));
            var red_scarf = itemRepository.findByName("red scarf");

            itemRepository.save(new Item("periwinkle scarf",
                    "periwinkle",
                    "cotton",
                    "L",
                    "https://res.cloudinary.com/toyefule/image/upload/c_fill,g_face,h_150,r_50,w_150/v1554135255/Outfit/Accessories/Periwinkle_Scarf.jpg",
                    "women's wear",
                    mel,
                    accessories,
                    cold,
                    casual,
                    moderate));
            var periwinkle = itemRepository.findByName("periwinkle scarf");

            itemRepository.save(new Item("burberry scarf",
                    "Burberry",
                    "cotton",
                    "L",
                    "https://res.cloudinary.com/toyefule/image/upload/c_fill,g_face,h_150,r_50,w_150/v1554135255/Outfit/Accessories/Burberry_Scarf.jpg",
                    "women's wear",
                    mel,
                    accessories,
                    cold,
                    casual,
                    moderate));
            var burberryscarf = itemRepository.findByName("burberry scarf");

            itemRepository.save(
                    new Item("grey sperry loafer2",
                            "Grey",
                            "leather",
                            "10",
                            "https://res.cloudinary.com/toyefule/image/upload/c_fill,g_face,h_150,r_50,w_150/v1554132719/Outfit/Shoes/Grey_Sperry_Loafer.jpg",
                            "men's wear",
                            dag,
                            shoe,
                            cold,
                            casual,
                            moderate));
            shirt = itemRepository.findByName("grey sperry loafer2");

            itemRepository.save(
                    new Item("flat front chino",
                            "brown",
                            "cotton",
                            "medium",
                            "https://res.cloudinary.com/toyefule/image/upload/c_fill,g_face,h_150,r_50,w_150/v1554134318/Outfit/Bottom/dag_flat_chino.jpg",
                            "men's wear",
                            dag,
                            bottom,
                            cold,
                            casual, moderate));
            pant = itemRepository.findByName("flat front chino");

            itemRepository.save(
                    new Item("winter hoodie vest2",
                            "Black",
                            "cotton",
                            "large",
                            "https://res.cloudinary.com/toyefule/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553797632/Outfit/Black_Light-Grey_2.jpg",
                            "men's wear",
                            dag,
                            jacket,
                            cold,
                            casual, moderate));
            lightJacket = itemRepository.findByName("winter hoodie vest2");

    /*//moe items
        itemRepository.save(new Item("shirt",
                "white",
                "cotton",
                "small",
                "https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553756502/outfit/Tops/Cold/Grey_Long_Sleeve.jpg",
                "men's wear",
                moe,
                top,
                cold,
                dinner));
        var shirt = itemRepository.findByName("shirt");

        itemRepository.save(
                new Item("pant",
                        "white",
                        "polyester",
                        "medium",
                        "https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553756501/outfit/Bottom/Cold/Jeans_Blue_1.jpg",
                        "men's wear",
                        moe,
                        bottom,
                        cold,
                        casual));
        var pant = itemRepository.findByName("pant");

        itemRepository.save(
                new Item("light jacket",
                        "white",
                        "leather",
                        "large",
                        "https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553756501/outfit/Jackets/Warm/Black_Nike_WIndbreaker.jpg",
                        "men's wear",
                        moe,
                        jacket,
                        mild,
                        casual));
        var lightJacket = itemRepository.findByName("light jacket");

        itemRepository.save(
                new Item("sandle",
                        "white",
                        "leather",
                        "US 10",
                        "https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553756502/outfit/Shoes/Warm/Warm_Midhigh_Socks.jpg",
                        "men's wear",
                        moe,
                        shoe,
                        hot,
                        casual));
        var sandle = itemRepository.findByName("sandle");
*/
        }
    }
}