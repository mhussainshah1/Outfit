package com.example.demo.business.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    @Size(min=4)
    private String top;

    @NotEmpty
    @Size(min=4)
    private String bottoms;

    @NotEmpty
    @Size(min=4)
    private String Jackets;

    @NotEmpty
    @Size(min=4)
    private String shoes;

    @OneToMany(cascade = ALL, mappedBy = "category")
    public Set<Item> items;

    public Category() {
        items = new HashSet<>();
    }

    public Category(@NotEmpty @Size(min = 4) String top, @NotEmpty @Size(min = 4) String bottoms, @NotEmpty @Size(min = 4) String jackets, @NotEmpty @Size(min = 4) String shoes) {
        this.top = top;
        this.bottoms = bottoms;
        Jackets = jackets;
        this.shoes = shoes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getBottoms() {
        return bottoms;
    }

    public void setBottoms(String bottoms) {
        this.bottoms = bottoms;
    }

    public String getJackets() {
        return Jackets;
    }

    public void setJackets(String jackets) {
        Jackets = jackets;
    }

    public String getShoes() {
        return shoes;
    }

    public void setShoes(String shoes) {
        this.shoes = shoes;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
