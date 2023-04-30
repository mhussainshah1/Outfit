package com.outfit.business.services;

import com.outfit.persistence.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getEmail();
        String firstName = oAuth2User.getFirstName();
        String lastName = oAuth2User.getLastName();
        String clientName = oAuth2User.getClientName();
        AuthenticationProvider provider = AuthenticationProvider.valueOf(clientName.toUpperCase());
        User user = userService.findByEmail(email);
        if (user == null) {
            //register new user
            userService.createNewUserAfterOAuthLoginSuccess(email, firstName, lastName, provider);
        } else {
            //update existing user
            userService.updateUserAfterOathLoginSuccess(user, provider);
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
