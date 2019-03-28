package com.example.demo.business.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class InvalidPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @NotEmpty
    @Column(unique = true)
    private String value;

    public InvalidPassword() {
    }

    public InvalidPassword(String value) {
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

