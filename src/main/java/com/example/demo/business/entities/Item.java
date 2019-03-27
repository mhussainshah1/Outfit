package com.example.demo.business.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 4)
    private String color;

    @NotNull
    @Size(min = 3)
    private String material;

    @NotNull
    private boolean size;

    //@NotNull
    //@Size(min = 4)
    private String picturePath;

    @NotNull
    @Size(min = 10)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private Set<Temperature> temperature;

    public Item() {
    }

    public Item(@NotNull @Size(min = 4) String color, @NotNull @Size(min = 3) String material, @NotNull boolean size, String picturePath, @NotNull @Size(min = 10) String description) {
        this.color = color;
        this.material = material;
        this.size = size;
        this.picturePath = picturePath;
        this.description = description;
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

    public boolean isSize() {
        return size;
    }

    public void setSize(boolean size) {
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


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Set<Temperature> getTemperature() {
        return temperature;
    }

    public void setTemperature(Set<Temperature> temperature) {
        this.temperature = temperature;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
