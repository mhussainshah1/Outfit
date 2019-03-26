package com.example.demo.business.entities;

import javax.persistence.*;
import java.util.Set;

public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique=true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private Set<Item> item;
}
