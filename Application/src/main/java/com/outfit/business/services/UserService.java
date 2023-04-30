package com.outfit.business.services;

import com.outfit.business.entities.User;
import com.outfit.business.entities.repositories.RoleRepository;
import com.outfit.business.entities.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Long countByEmail(String email) {
        return userRepository.countByEmail(email);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void saveUser(User user) {
        user.setRoles(Arrays.asList(roleRepository.findByRole("USER"))
                .stream()
                .collect(Collectors.toSet()));
        user.setEnabled(true);
        userRepository.save(user);
    }

    public void saveAdmin(User user) {
        user.setRoles(Arrays.asList(roleRepository.findByRole("ADMIN"))
                .stream()
                .collect(Collectors.toSet()));
        user.setEnabled(true);
        userRepository.save(user);
    }

    // returns currently logged-in user
    public User getUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        Object user = authentication.getPrincipal();//user
        String email = null;
        if (user instanceof CustomOAuth2User) {
            email = ((CustomOAuth2User) user).getAttribute("email");
        }
        if(user instanceof CustomUserDetails) {
            email = ((CustomUserDetails) user).getUser().getEmail();
        }
        return userRepository.findByEmail(email);
    }

/*    public String encode(String password) {
        var passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }*/

    public boolean isAdmin() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities()
                .stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
    }

    public boolean isUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities()
                .stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_USER") || r.getAuthority().equals("OAUTH2_USER"));
    }

    public void createNewUserAfterOAuthLoginSuccess(String email, String firstName,String lastName, AuthenticationProvider provider) {
        User user = new User(email, "", firstName, lastName, true, email, provider);
        userRepository.save(user);

    }

    public void updateUserAfterOathLoginSuccess(User user, AuthenticationProvider provider) {
        user.setAuthProvider(provider);
        userRepository.save(user);
    }
}
