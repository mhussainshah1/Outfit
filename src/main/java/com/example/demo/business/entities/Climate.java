package com.example.demo.business.entities;

import javax.persistence.*;
import java.util.Set;

//Hot Mild Cold
@Entity
public class Climate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(mappedBy = "climate",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Item> item;

    public Climate() {
    }

    public Climate(String name) {
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
