package com.example.demo.business.entities;

import javax.persistence.*;
//import javax.validation.constraints.NotEmpty;
import java.util.Set;


@Entity
public class Wind {
    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "wind", cascade = CascadeType.ALL)
    private Set<Item> item;

    public Wind() {
    }

    public Wind(String name) {

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
