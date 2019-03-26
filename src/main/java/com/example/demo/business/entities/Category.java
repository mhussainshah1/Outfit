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
    private String bottom;

    @NotEmpty
    @Size(min=4)
    private String Jacket;

    @NotEmpty
    @Size(min=4)
    private String shoe;

    @OneToMany(cascade = ALL, mappedBy = "category")
    public Set<Item> items;

    public Category() {
        items = new HashSet<>();
    }

    public Category(@NotEmpty @Size(min = 4) String top, @NotEmpty @Size(min = 4) String bottom, @NotEmpty @Size(min = 4) String jacket, @NotEmpty @Size(min = 4) String shoe) {
        this.top = top;
        this.bottom = bottom;
        Jacket = jacket;
        this.shoe = shoe;
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

    public String getBottom() {
        return bottom;
    }

    public void setBottom(String bottom) {
        this.bottom = bottom;
    }

    public String getJacket() {
        return Jacket;
    }

    public void setJacket(String jacket) {
        Jacket = jacket;
    }

    public String getShoe() {
        return shoe;
    }

    public void setShoe(String shoe) {
        this.shoe = shoe;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
