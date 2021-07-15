package com.outfit.business.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Entity
public class Item implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    @Column(unique = true)
    private String name;

    @NotEmpty
    private String color;

    @NotEmpty
    private String material;

    @NotEmpty
    private String size;

    @NotNull
    private String picturePath;

    @NotEmpty
    private String description;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Climate climate;

    @ManyToOne
    private Occasion occasion;

    @ManyToOne
    private Wind wind;

    public Item() {
        picturePath = "";
        user = new User();
        category = new Category();
        climate = new Climate();
        occasion = new Occasion();
        wind = new Wind();
    }

    public Item(@NotEmpty String name, @NotEmpty String color, @NotEmpty String material, @NotEmpty String size, @NotNull String picturePath, @NotEmpty String description, User user, Category category, Climate climate, Occasion occasion, Wind wind) {
        this.name = name;
        this.color = color;
        this.material = material;
        this.size = size;
        this.picturePath = picturePath;
        this.description = description;
        this.user = user;
        this.category = category;
        this.climate = climate;
        this.occasion = occasion;
        this.wind = wind;
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

    public Climate getClimate() {
        return climate;
    }

    public void setClimate(Climate climate) {
        this.climate = climate;
    }

    public Occasion getOccasion() {
        return occasion;
    }

    public void setOccasion(Occasion occasion) {
        this.occasion = occasion;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }


    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", material='" + material + '\'' +
                ", size='" + size + '\'' +
                ", picturePath='" + picturePath + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user.getUsername() +
                ", category=" + category.getName() +
                ", climate=" + climate.getName() +
                ", occasion=" + occasion.getName() +
                ", wind=" + wind.getName() +
                '}';
    }
}
