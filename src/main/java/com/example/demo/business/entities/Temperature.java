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
    private Set<Item> item;

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


    public Set<Item> getItem() {
        return item;
    }

    public void setItem(Set<Item> item) {
        this.item = item;
    }
}
