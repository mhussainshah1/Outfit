package com.example.demo.business.entities;

import javax.persistence.*;
import java.util.Set;

//Hot Mild Cold
@Entity
public class Temperature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(mappedBy = "temperature",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Item> items;


    public Temperature() {
    }

    public Temperature(String name) {
        this.name = name;
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


    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
