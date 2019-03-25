package com.example.demo.business.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min=4)
    private String color;

    @NotNull
    @Size(min=3)
    private String fabric;

    @NotNull
    private boolean longShort;

    //@NotNull
    //@Size(min = 4)
    private String picturePath;

    @NotNull
    @Size(min=10)
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Item() {
    }

    public Item(@NotNull @Size(min = 4) String color, @NotNull @Size(min = 3) String fabric, @NotNull boolean longShort, String picturePath, @NotNull @Size(min = 10) String description) {
        this.color = color;
        this.fabric = fabric;
        this.longShort = longShort;
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

    public String getFabric() {
        return fabric;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }

    public boolean isLongShort() {
        return longShort;
    }

    public void setLongShort(boolean longShort) {
        this.longShort = longShort;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", fabric='" + fabric + '\'' +
                ", longShort=" + longShort +
                ", picturePath='" + picturePath + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                '}';
    }
}
