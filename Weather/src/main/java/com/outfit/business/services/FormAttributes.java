package com.outfit.business.services;

import org.springframework.context.annotation.Bean;

import jakarta.validation.constraints.NotEmpty;
//@Service

public class FormAttributes {

    @NotEmpty
    private String city;

    public FormAttributes() {
    }

    public FormAttributes(@NotEmpty String city) {
        this.city = city;
    }

    @Bean
    public FormAttributes formAttributes() {
        return new FormAttributes();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
