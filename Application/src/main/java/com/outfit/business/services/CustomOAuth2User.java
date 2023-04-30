package com.outfit.business.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {
    private OAuth2User oAuth2User;

    private String clientName;

    public CustomOAuth2User(OAuth2User oAuth2User, String clientName) {
        this.oAuth2User = oAuth2User;
        this.clientName = clientName;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oAuth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return oAuth2User.getAttribute("name");
    }

    public String getClientName() {
        return clientName;
    }

    public String getFirstName() {
        String[] names = getName().split(" ");
        String firstName = names[0];// = oAuth2User.getAttribute("given_name") //It only works with a Google account
        String lastName = names[names.length - 1];
        return firstName;
    }

    public String getLastName() {
        String[] names = getName().split(" ");
        String firstName = names[0];
        String lastName = names[names.length - 1];// = oAuth2User.getAttribute("family_name") //It only works with a Google account
        return lastName;
    }

    public String getEmail() {
        return oAuth2User.getAttribute("email");
    }

    @Override
    public String toString() {
        return "CustomOAuth2User{" +
                "oAuth2User=" + oAuth2User +
                '}';
    }
}