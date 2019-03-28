package com.example.demo.business.services;

import com.example.demo.business.entities.*;
import com.example.demo.business.entities.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    SeasonRepository seasonRepository;

    @Autowired
    TemperatureRepository temperatureRepository;

    @Autowired
    InvalidPasswordRepository invalidPasswordRepository;

    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));

        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        invalidPasswordRepository.save(new InvalidPassword("azerty12!"));
        invalidPasswordRepository.save(new InvalidPassword("12345678!"));
        invalidPasswordRepository.save(new InvalidPassword("password123"));

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

        User admin = new User("admin@admin.com", "Pa$$word2019", "Admin", "User", true, "admin");
        admin.setPassword(userService.encode(admin.getPassword()));
        userService.saveUser(admin);

        /*Course course = new Course("Astrophysics", "Neil D Tyson", "Just a course on stars", 3);
        course.setUser(nan);
        courseRepository.save(course);

        course = new Course("Calculus", "Carol Henley", "Rate of change of rate of change", 3);
        course.setUser(moe);
        courseRepository.save(course);

        course = new Course("Freshman English", "Geraldine Pegram", "Learn your language chilern", 3);
        course.setUser(tolani);
        courseRepository.save(course);*/

        categoryRepository.save(new Category("Top"));
        Category top = categoryRepository.findByName("Top");

        categoryRepository.save(new Category("Bottom"));
        Category bottom = categoryRepository.findByName("Bottom");

        categoryRepository.save(new Category("Jacket"));
        Category jacket = categoryRepository.findByName("Jacket");

        categoryRepository.save(new Category("Shoes"));
        Category shoe = categoryRepository.findByName("Shoes");

       /* seasonRepository.save(new Season("fall"));
        Season fall = seasonRepository.findByName("fall");
        Season winter = new Season("winter");
        Season spring = new Season("spring");
        Season summer = new Season("summer");*/

        temperatureRepository.save(new Temperature("cold"));
        Temperature cold = temperatureRepository.findByName("cold");

        temperatureRepository.save(new Temperature("hot"));
        Temperature hot = temperatureRepository.findByName("hot");

        temperatureRepository.save(new Temperature("mild"));
        Temperature mild = temperatureRepository.findByName("mild");

        temperatureRepository.save(new Temperature("rainy"));
        Temperature rainy = temperatureRepository.findByName("rainy");

        itemRepository.save( new Item("shirt",
                "white",
                "cotton",
                "small",
                "https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553756502/outfit/Tops/Cold/Grey_Long_Sleeve.jpg",
                "men",
                moe,
                top,
                cold));
        Item shirt = itemRepository.findByName("shirt");

        itemRepository.save(new Item("pant",
                "white",
                "polyester",
                "small",
                "https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553756501/outfit/Bottom/Cold/Jeans_Blue_1.jpg",
                "men",
                moe,
                bottom,
                cold));
        Item pant = itemRepository.findByName("pant");

        itemRepository.save(new Item("light jacket",
                "white",
                "leather",
                "small",
                "https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553756501/outfit/Jackets/Warm/Black_Nike_WIndbreaker.jpg",
                "men",
                moe,
                jacket,
                mild));
        Item lightJacket = itemRepository.findByName("light jacket");

        itemRepository.save(new Item("sandle",
                "white",
                "leather",
                "US 10",
                "https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553756502/outfit/Shoes/Warm/Warm_Midhigh_Socks.jpg",
                "men",
                moe,
                shoe,
                hot));
        Item sandle = itemRepository.findByName("sandle");

       /* Item sandles = new Item("white",
                "leather",
                "L",
                "https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553093077/java-bootcamp/nsgop8vivttqeqkkkynr.jpg",
                "men",
                moe,
                shoe);
        temps = new HashSet<>();
        temps.add(cold);
        sandles.setTemperature(temps);
        itemRepository.save(sandles);

        cold.setItem(sandles);
        temperatureRepository.save(cold);*/

       /* Car car1 = new Car("Honda","Accord",2019,"35 miles/gallon",45000.55,"https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1552081999/java-bootcamp/bo1q7fwi8qytkxi6yyus.jpg",category);
        category.getCars().add(car1);
        Car car2 = new Car("Toyota","Camry",2018,"35 miles/gallon",32000.02,"https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1552100700/java-bootcamp/mj2tywvdhsq0mleow3rp.png",category);
        category.getCars().add(car2);
        categoryRepository.save(category);
        carRepository.save(car1);
        carRepository.save(car2);*/


        /*car = new Car("Mercedez","Benz",2019,"35 miles/gallon", 50000.85,"https://res.cloudinary.com/mhussainshah1/image/upload/c_fill,g_face,h_150,r_50,w_150/v1552162481/java-bootcamp/klztzsqldrhak8lvs7zu.png",category);
        category.getCars().add(car);
        categoryRepository.save(category);
        carRepository.save(car);
*/


    }
}
