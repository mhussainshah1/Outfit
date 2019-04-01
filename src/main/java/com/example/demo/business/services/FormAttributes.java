package com.example.demo.business.services;

import javax.validation.constraints.NotEmpty;

//@Service
public class FormAttributes {

    @NotEmpty
    private String city;

    public FormAttributes() {
    }

    public FormAttributes(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
