package com.example.demo.business.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Occasion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    @Column(unique = true)
    private String name;

    public Occasion() {
    }

    public Occasion(String name) {
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
