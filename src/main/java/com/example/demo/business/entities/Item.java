package com.example.demo.business.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String color;

    @NotEmpty
    private String material;

    @NotEmpty
    private String size;

    //@NotNull
    //@Size(min = 4)
    private String picturePath;

    @NotEmpty
    private String description;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Temperature temperature;

    public Item() {
    }

    public Item(@NotEmpty String name, @NotEmpty String color, @NotEmpty String material, @NotEmpty String size, String picturePath, @NotEmpty String description, User user, Category category, Temperature temperature) {
        this.name = name;
        this.color = color;
        this.material = material;
        this.size = size;
        this.picturePath = picturePath;
        this.description = description;
        this.user = user;
        this.category = category;
        this.temperature = temperature;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", material='" + material + '\'' +
                ", size=" + size +
                ", picturePath='" + picturePath + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                '}';
    }


}
