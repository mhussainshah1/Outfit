package com.example.demo.business.services;

import com.example.demo.business.entities.*;
import com.example.demo.business.entities.repositories.*;
import com.example.demo.business.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

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
            Set<InvalidPassword> passwords = new HashSet<>();
            passwords.add(new InvalidPassword("azerty12!"));
            passwords.add(new InvalidPassword("12345678!"));
            passwords.add(new InvalidPassword("password123"));
            invalidPasswordRepository.saveAll(passwords);

            //Role
            roleRepository.save(new Role("USER"));
            roleRepository.save(new Role("ADMIN"));

            Role userRole = roleRepository.findByRole("USER");
            Role adminRole = roleRepository.findByRole("ADMIN");

            //User
            User admin = new User("admin@admin.com", "password", "Admin", "User", true, "admin");
            admin.setPassword(userService.encode(admin.getPassword()));
            userService.saveAdmin(admin);

            User moe = new User("mhussainshah79@gmail.com", "password", "Muhammad", "Shah", true, "moe");
            moe.setPassword(userService.encode(moe.getPassword()));
            userService.saveUser(moe);

            User lan = new User("tolani.oyefule@gmail.com", "password", "Tolani", "Oyefule", true, "lan");
            lan.setPassword(userService.encode(lan.getPassword()));
            userService.saveUser(lan);

            User nan = new User("nhan.cog.huynh@gmail.com", "password", "Nhan", "Huynh", true, "nan");
            nan.setPassword(userService.encode(nan.getPassword()));
            userService.saveUser(nan);

            User dag = new User("dag@gmail.com", "password", "Dag", "Fasil", true, "dag");
            dag.setPassword(userService.encode(dag.getPassword()));
            userService.saveUser(dag);

            User mel = new User("melissafong@gmail.com", "password", "Mellisa", "Lavander", true, "mel");
            mel.setPassword(userService.encode(mel.getPassword()));
            userService.saveUser(mel);

            User jen = new User("jen@gmail.com", "password", "Jennifer", "You", true, "jen");
            jen.setPassword(userService.encode(jen.getPassword()));
            userService.saveUser(jen);

            //Category
            categoryRepository.save(new Category("top"));
            Category top = categoryRepository.findByName("top");

            categoryRepository.save(new Category("glasses"));
            Category glasses = categoryRepository.findByName("glasses");

            categoryRepository.save(new Category("jacket"));
            Category jacket = categoryRepository.findByName("jacket");

            categoryRepository.save(new Category("bottom"));
            Category bottom = categoryRepository.findByName("bottom");

            categoryRepository.save(new Category("socks"));
            Category socks = categoryRepository.findByName("socks");

            categoryRepository.save(new Category("shoes"));
            Category shoe = categoryRepository.findByName("shoes");

            categoryRepository.save(new Category("accessories"));
            Category accessories = categoryRepository.findByName("accessories");

            //Climate
            climateRepository.save(new Climate("cold"));
            Climate cold = climateRepository.findByName("cold");

            climateRepository.save(new Climate("moderate"));
            Climate mild = climateRepository.findByName("moderate");

            climateRepository.save(new Climate("hot"));
            Climate hot = climateRepository.findByName("hot");

            climateRepository.save(new Climate("rainy"));
            Climate rainy = climateRepository.findByName("rainy");

            //Occasion
            occasionRepository.save(new Occasion("active"));
            Occasion active = occasionRepository.findByName("active");

            occasionRepository.save(new Occasion("party"));
            Occasion party = occasionRepository.findByName("party");

            occasionRepository.save(new Occasion("nightlife"));
            Occasion nightlife = occasionRepository.findByName("nightlife");

            occasionRepository.save(new Occasion("meeting"));
            Occasion meeting = occasionRepository.findByName("meeting");

            occasionRepository.save(new Occasion("casual"));
            Occasion casual = occasionRepository.findByName("casual");

            occasionRepository.save(new Occasion("formal"));
            Occasion formal = occasionRepository.findByName("formal");

            occasionRepository.save(new Occasion("dinner"));
            Occasion dinner = occasionRepository.findByName("dinner");

            //Wind
            windRepository.save(new Wind("light"));
            Wind light = windRepository.findByName("light");

            windRepository.save(new Wind("moderate"));
            Wind moderate = windRepository.findByName("moderate");

            windRepository.save(new Wind("high"));
            Wind high = windRepository.findByName("high");

            windRepository.save(new Wind("periodic"));
            Wind periodic = windRepository.findByName("periodic");

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
            Item Homeboyshirt = itemRepository.findByName("homeboy shirt");

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
            Item pant = itemRepository.findByName("cargo pant");

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
            Item lightJacket = itemRepository.findByName("light jacket");

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
            Item sandle = itemRepository.findByName("socks");

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
            Item sneakers = itemRepository.findByName("ball shorts white");

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
            Item shirt = itemRepository.findByName("long sleeve shirt");

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
            Item jeans = itemRepository.findByName("jeans");

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
            Item bluewhiteJacket = itemRepository.findByName("white & blue windbreaker jacket");

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
            Item pishirt = itemRepository.findByName("short sleeve Shirt");

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
            Item greengreyJacket = itemRepository.findByName("green & grey wind breaker jacket");

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
            Item khakishorts = itemRepository.findByName("khaki shorts");

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
            Item sandals = itemRepository.findByName("all weather sandles");

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
            Item cream_scarf = itemRepository.findByName("cream scarf");

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
            Item red_scarf = itemRepository.findByName("red scarf");

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
            Item periwinkle = itemRepository.findByName("periwinkle scarf");

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
            Item burberryscarf = itemRepository.findByName("burberry scarf");

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
        Item shirt = itemRepository.findByName("shirt");

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
        Item pant = itemRepository.findByName("pant");

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
        Item lightJacket = itemRepository.findByName("light jacket");

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
        Item sandle = itemRepository.findByName("sandle");
*/
        }
    }
}