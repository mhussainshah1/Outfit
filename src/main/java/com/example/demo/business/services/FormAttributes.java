package com.example.demo.business.services;

import org.springframework.stereotype.Service;

@Service
public class FormAttributes {

    private String city;

    public FormAttributes() {
        super();
    }

    public FormAttributes(String city) {
        super();
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
