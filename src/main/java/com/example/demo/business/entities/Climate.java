package com.example.demo.business.entities;

import javax.persistence.*;
import java.util.Set;

//Hot Mild Cold
@Entity
public class Climate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String name;


    @OneToMany(mappedBy = "climate",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Item> items;

    @OneToMany(mappedBy = "", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

}
