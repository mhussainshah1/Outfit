package com.example.demo.business.services;

import com.example.demo.business.entities.*;
import com.example.demo.business.entities.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
            //if you want to run dataloader then change it to true.
            // it will help you to not comment out whole class

            //Role
            roleRepository.save(new Role("USER"));
            roleRepository.save(new Role("ADMIN"));

            Role adminRole = roleRepository.findByRole("ADMIN");
            Role userRole = roleRepository.findByRole("USER");

            //Password
            invalidPasswordRepository.save(new InvalidPassword("azerty12!"));
            invalidPasswordRepository.save(new InvalidPassword("12345678!"));
            invalidPasswordRepository.save(new InvalidPassword("password123"));

            //User
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

            User admin = new User("admin@admin.com", "password", "Admin", "User", true, "admin");
            admin.setPassword(userService.encode(admin.getPassword()));
            userService.saveAdmin(admin);

            //Category
            categoryRepository.save(new Category("Top"));
            Category top = categoryRepository.findByName("Top");

            categoryRepository.save(new Category("Glasses"));
            Category glasses = categoryRepository.findByName("Glasses");

            categoryRepository.save(new Category("Jacket"));
            Category jacket = categoryRepository.findByName("Jacket");

            categoryRepository.save(new Category("Bottom"));
            Category bottom = categoryRepository.findByName("Bottom");

            categoryRepository.save(new Category("Socks"));
            Category socks = categoryRepository.findByName("Socks");

            categoryRepository.save(new Category("Shoes"));
            Category shoe = categoryRepository.findByName("Shoes");

            categoryRepository.save(new Category("Accessories"));
            Category accessories = categoryRepository.findByName("Accessories");

            //Climate
            climateRepository.save(new Climate("Cold"));
            Climate cold = climateRepository.findByName("Cold");

            climateRepository.save(new Climate("Moderate"));
            Climate mild = climateRepository.findByName("Moderate");

            climateRepository.save(new Climate("Hot"));
            Climate hot = climateRepository.findByName("Hot");

            climateRepository.save(new Climate("Rainy"));
            Climate rainy = climateRepository.findByName("Rainy");

            //Occasion
            occasionRepository.save(new Occasion("Active"));
            Occasion active = occasionRepository.findByName("Active");

            occasionRepository.save(new Occasion("Party"));
            Occasion party = occasionRepository.findByName("Party");

            occasionRepository.save(new Occasion("NightLife"));
            Occasion nightlife = occasionRepository.findByName("NightLife");

            occasionRepository.save(new Occasion("Meeting"));
            Occasion meeting = occasionRepository.findByName("Meeting");

            occasionRepository.save(new Occasion("Casual"));
            Occasion casual = occasionRepository.findByName("Casual");

            occasionRepository.save(new Occasion("Formal"));
            Occasion formal = occasionRepository.findByName("Formal");

            occasionRepository.save(new Occasion("Dinner"));
            Occasion dinner = occasionRepository.findByName("Dinner");

            //Wind
            windRepository.save(new Wind("Light"));
            Wind light = windRepository.findByName("Light");

            windRepository.save(new Wind("Moderate"));
            Wind moderate = windRepository.findByName("Moderate");

            windRepository.save(new Wind("High"));
            Wind high = windRepository.findByName("High");

            windRepository.save(new Wind("Periodic"));
            Wind periodic = windRepository.findByName("Periodic");

            //moe items
            itemRepository.save(new Item("Homeboy shirt",
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
            Item Homeboyshirt = itemRepository.findByName("Homeboy shirt");

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
                    new Item("Ball shorts White",
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
            Item sneakers = itemRepository.findByName("Ball Shorts White");


            //tolani item
            itemRepository.save(new Item("long Sleeve Shirt",
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
            Item shirt = itemRepository.findByName("long Sleeve shirt");

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
                    new Item("White and Blue Windbreaker jacket",
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
            Item bluewhiteJacket = itemRepository.findByName("White and Blue Windbreaker jacket");

            itemRepository.save(
                    new Item("Nike Sneakers",
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
            sneakers = itemRepository.findByName("Nike Sneakers");

            itemRepository.save(
                    new Item("Timb All-weather-Boots",
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
            sneakers = itemRepository.findByName("Timberland Boots");

            itemRepository.save(
                    new Item("Ball shorts Black",
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
            sneakers = itemRepository.findByName("Ball Shorts Black");


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
                    new Item("Green and Grey Wind breaker jacket",
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
            Item greengreyJacket = itemRepository.findByName("Green and Grey Wind breaker jacket");

            itemRepository.save(
                    new Item("Ball shorts Grey",
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
            sneakers = itemRepository.findByName("Ball Shorts Grey");

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
                    new Item("All weather sandles",
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
            Item sandals = itemRepository.findByName("All weather sandles");

            //dag item
            itemRepository.save(new Item("Grey Sperry Loafer",
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
            shirt = itemRepository.findByName("Flat Front Chino");

            itemRepository.save(
                    new Item("pant",
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
            pant = itemRepository.findByName("Khaki Brown pants");

            itemRepository.save(
                    new Item("Winter Hoodie Vest",
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
            lightJacket = itemRepository.findByName("Winter hoodie Vest ");

            itemRepository.save(
                    new Item("Ball shorts Blue",
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
            sneakers = itemRepository.findByName("Ball Shorts Blue");

            //mel item
            itemRepository.save(new Item("Cream Scarf",
                    "cream ",
                    "cotton",
                    "L",
                    "https://res.cloudinary.com/toyefule/image/upload/c_fill,g_face,h_150,r_50,w_150/v1554135255/Outfit/Accessories/Cream_Scarf.jpg",
                    "women's wear",
                    mel,
                    accessories,
                    cold,
                    casual, moderate));
            Item cream_scarf = itemRepository.findByName("Cream Scarf");

            itemRepository.save(new Item("Red Scarf",
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

            Item red_scarf = itemRepository.findByName("Red Scarf");

            itemRepository.save(new Item("Periwinkle Scarf",
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
            Item periwinkle = itemRepository.findByName("Periwinkle Scarf");

            itemRepository.save(new Item("Burberry Scarf",
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
            Item burberryscarf = itemRepository.findByName("Burberry Scarf");

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