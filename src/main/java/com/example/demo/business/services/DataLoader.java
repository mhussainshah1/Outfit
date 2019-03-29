package com.example.demo.business.services;

import com.example.demo.business.entities.*;
import com.example.demo.business.entities.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    OccasionRepository occasionRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ClimateRepository climateRepository;

    @Autowired
    OccasionRepository occasionRepository;


    @Autowired
    InvalidPasswordRepository invalidPasswordRepository;

    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {

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

        User tolani = new User("tolani.oyefule@gmail.com", "password", "Tolani", "Oyefule", true, "lan");
        tolani.setPassword(userService.encode(tolani.getPassword()));
        userService.saveUser(tolani);

        User nan = new User("nhan.cog.huynh@gmail.com", "password", "Nhan", "Huynh", true, "nan");
        nan.setPassword(userService.encode(nan.getPassword()));
        userService.saveUser(nan);

        User dag = new User("dag@gmail.com", "password", "Dag", "Fasil", true, "dag");
        dag.setPassword(userService.encode(dag.getPassword()));
        userService.saveUser(dag);

        User mel = new User("melissaAfung@gmail.com", "password", "Melissa", "Fung", true, "mel");
        mel.setPassword(userService.encode(mel.getPassword()));
        userService.saveUser(mel);

        User admin = new User("admin@admin.com", "password", "Admin", "User", true, "admin");
        admin.setPassword(userService.encode(admin.getPassword()));
        userService.saveUser(admin);

        categoryRepository.save(new Category("Top"));
        Category top = categoryRepository.findByName("Top");

        //Category
        categoryRepository.save(new Category("Bottom"));
        Category bottom = categoryRepository.findByName("Bottom");

        categoryRepository.save(new Category("Jacket"));
        Category jacket = categoryRepository.findByName("Jacket");

<<<<<<< HEAD
        categoryRepository.save(new Category("Top"));
        Category top = categoryRepository.findByName("Top");
     /*   category = new Category("Bottom");
        category = new Category("Jacket");
        category = new Category("Shoes");*/

       /* seasonRepository.save(new Season("fall"));
        Season fall = seasonRepository.findByName("fall");
        Season winter = new Season("winter");
        Season spring = new Season("spring");
        Season summer = new Season("summer");*/

        temperatureRepository.save(new Temperature("cold"));
        Temperature cold = temperatureRepository.findByName("cold");
        /*Temperature hot = new Temperature("hot");
        Temperature mild = new Temperature("mild");
        Temperature rainy = new Temperature("rainy");*/

        occasionRepository.save(new Occasion("casual"));
        Occasion casual = occasionRepository.findByName("casual");
        /*Occasion formal = new Temperature("formal");
        Occasion business_casual = new Temperature("business casual"); */





        itemRepository.save(new Item( "shirt",
                    /*color*/        "white",
                    /*material*/     "Cotton",
                    /*size*/         "s",
                    /*description*/  "              ",
                    /*picturePath*/  "https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553093077/java-bootcamp/nsgop8vivttqeqkkkynr.jpg",
                    /*user*/          moe,
                    /*category*/      top,
                    /*temperature*/   cold,
                    /*occasion*/      casual
        ));
        Item shirt = itemRepository.findByName("shirt");
       /* Car car1 = new Car("Honda","Accord",2019,"35 miles/gallon",45000.55,"https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1552081999/java-bootcamp/bo1q7fwi8qytkxi6yyus.jpg",category);
=======
        categoryRepository.save(new Category("Shoes"));
        Category shoe = categoryRepository.findByName("Shoes");

        categoryRepository.save(new Category("Socks"));
        Category socks = categoryRepository.findByName("Socks");


        //Climate
        climateRepository.save(new Climate("Cold"));
        Climate cold = climateRepository.findByName("Cold");

        climateRepository.save(new Climate("Mild"));
        Climate mild = climateRepository.findByName("Mild");

        climateRepository.save(new Climate("Hot"));
        Climate hot = climateRepository.findByName("Hot");

        climateRepository.save(new Climate("Rainy"));
        Climate rainy = climateRepository.findByName("Rainy");

        //Occasion
        occasionRepository.save(new Occasion("Party"));
        Occasion party = occasionRepository.findByName("Party");

        occasionRepository.save(new Occasion("Meeting"));
        Occasion meeting = occasionRepository.findByName("Meeting");

        occasionRepository.save(new Occasion("Casual"));
        Occasion casual = occasionRepository.findByName("Casual");

        occasionRepository.save(new Occasion("Formal"));
        Occasion formal = occasionRepository.findByName("Formal");

        occasionRepository.save(new Occasion("Dinner"));
        Occasion dinner = occasionRepository.findByName("Dinner");

        //moe items
        itemRepository.save(new Item("shirt",
                "grey",
                "cotton",
                "small",
                "https://res.cloudinary.com/toyefule/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553799014/Outfit/JESUS_IS_MY_HOMEBOY_heathergrey_2000x.jpg",
                "men's wear",
                moe,
                top,
                cold,
                dinner));
        Item Homeboyshirt = itemRepository.findByName("Homeboyshirt");
                                //      c_fill,g_face,h_150,r_50,w_150/
        itemRepository.save(
                new Item("cargo pants",
                        "khaki",
                        "polyester",
                        "medium",
                        "//https://res.cloudinary.com/toyefule/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553802838/Outfit/Cargo_khaki_1.jpg",
                        "men's wear",
                        moe,
                        bottom,
                        cold,
                        casual));
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
                        casual));
        Item lightJacket = itemRepository.findByName("light jacket");

        itemRepository.save(
                new Item("socks",
                        "grey",
                        "cotton",
                        "US 10",
                        "https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553756502/outfit/Shoes/Warm/Warm_Midhigh_Socks.jpg",
                        "men's wear",
                        moe,
                        shoe,
                        hot,
                        casual));
        Item sandle = itemRepository.findByName("socks");

        //tolani item
        itemRepository.save(new Item("long Sleeve Shirt",
                "grey",
                "cotton",
                "small",
                "https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553756502/outfit/Tops/Cold/Grey_Long_Sleeve.jpg",
                "men's wear",
                tolani,
                top,
                cold,
                dinner));
        Item shirt = itemRepository.findByName("long Sleeve shirt");

        itemRepository.save(
                new Item("jeans",
                        "blue",
                        "cotton",
                        "medium",
                        "https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553756501/outfit/Bottom/Cold/Jeans_Blue_1.jpg",
                        "men's wear",
                        tolani,
                        bottom,
                        cold,
                        casual));
        Item jeans = itemRepository.findByName("jeans");

        itemRepository.save(
                new Item("White and Blue Windbreaker jacket",
                        "white",
                        "nylon",
                        "large",
                        "https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553756501/outfit/Jackets/Warm/Black_Nike_WIndbreaker.jpg",
                        "men's wear",
                        tolani,
                        jacket,
                        mild,
                        casual));
        Item bluewhiteJacket = itemRepository.findByName("Blue & White jacket");

        itemRepository.save(
                new Item("NikeSneakers",
                        "Black and Green",
                        "leather",
                        "US 10",
                        "https://res.cloudinary.com/toyefule/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553797544/Outfit/Nike-Outdoor-Green-Court-Borough-Mid-Winter-Shoes.jpg",
                        "men's wear",
                        tolani,
                        shoe,
                        hot,
                        casual));
        Item sneakers = itemRepository.findByName("Nike_Sneakers");

        //dag item
        /*itemRepository.save(

        );
        Item shirt = itemRepository.findByName("shirt");*/
        //nan item
        itemRepository.save(
                new Item("short sleeve Shirt",
        "BluePi",
        "cotton",
        "Medium",
        "https://res.cloudinary.com/toyefule/image/upload/v1553797471/Outfit/Blue_shortsleeve_Science_Shirt.jpg",
                "men's wear",
                nan,
                top,
                cold,
                dinner));
        Item pishirt = itemRepository.findByName("shirt");

        itemRepository.save(
                new Item("khaki shorts",
                        "Tan",
                        "cotton",
                        "medium",
                        "https://res.cloudinary.com/toyefule/image/upload/v1553797908/Outfit/Shorts_Khaki_1.jpg",
                        "men's wear",
                        nan,
                        bottom,
                        cold,
                        casual));
        Item khakishorts = itemRepository.findByName("jeans");

        itemRepository.save(
                new Item("Green and Grey Windbreaker jacket",
                        "Green Grey",
                        "nylon",
                        "large",
                        "https://res.cloudinary.com/toyefule/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553797638/Outfit/Green_Grey_1.jpg",
                        "men's wear",
                        nan,
                        jacket,
                        mild,
                        casual));
        Item greengreyJacket = itemRepository.findByName("Green & Grey jacket");

                itemRepository.save(
                        new Item("Sneakers",
                                "black and Green",
                                "leather",
                                "US 10",
                                "https://https://res.cloudinary.com/toyefule/image/upload/v1553797718/Outfit/Nhan_All-weather_Sandals.jpg",
                                "men's wear",
                                nan,
                                shoe,
                                cold,
                                casual));
        Item sandals = itemRepository.findByName("sandals");

        //mel item



       /* Item sandles = new Item("white",
                "leather",
                "L",
                "https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553093077/java-bootcamp/nsgop8vivttqeqkkkynr.jpg",
                "men",
                moe,
                shoe);
        temp.getClimate.add(cold);
        itemRepository.save(sandles);

        cold.setItem(sandles);
        climateRepository.save(cold);*/

       /*
        categoryRepository.save(category);

        Car car1 = new Car("Honda","Accord",2019,"35 miles/gallon",45000.55,"https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1552081999/java-bootcamp/bo1q7fwi8qytkxi6yyus.jpg",category);
>>>>>>> dc86bc62d3c015ca4901829c442aad821484968d
        category.getCars().add(car1);
        carRepository.save(car1);

        Car car2 = new Car("Toyota","Camry",2018,"35 miles/gallon",32000.02,"https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1552100700/java-bootcamp/mj2tywvdhsq0mleow3rp.png",category);
        category.getCars().add(car2);
        carRepository.save(car2);*/

        /*
        categoryRepository.save(category);

        car = new Car("Mercedez","Benz",2019,"35 miles/gallon", 50000.85,"https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1552162481/java-bootcamp/klztzsqldrhak8lvs7zu.png",category);
        category.getCars().add(car);
        carRepository.save(car);
*/
    }
}
