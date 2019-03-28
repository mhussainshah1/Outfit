package com.example.demo.business.entities;



import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    private String name;

    @NotNull
    private String color;

    @NotNull
    private String material;

    @NotEmpty
    private String size;

    @NotNull
    @Size(min = 10)
    private String description;

    //@NotNull
    //@Size(min = 4)
    private String picturePath;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Temperature temperature;

    @ManyToOne
    private Occasion occasion;

    public Item() {
    }

    public Item(@NotEmpty String name, @NotNull String color, @NotNull String material, @NotEmpty String size, @NotNull @Size(min = 10) String description, String picturePath, User user, Category category, Temperature temperature, Occasion occasion) {
        this.name = name;
        this.color = color;
        this.material = material;
        this.size = size;
        this.description = description;
        this.picturePath = picturePath;
        this.user = user;
        this.category = category;
        this.temperature = temperature;
        this.occasion = occasion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Occasion getOccasion() {
        return occasion;
    }

    public void setOccasion(Occasion occasion) {
        this.occasion = occasion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
