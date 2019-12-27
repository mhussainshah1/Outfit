package com.example.demo.business.services;

import com.example.demo.business.entities.User;
import com.example.demo.business.entities.repositories.RoleRepository;
import com.example.demo.business.entities.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    // returns currently logged in user
    public User getUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var currentUserName = authentication.getName();
        return userRepository.findByUsername(currentUserName);
    }

    public String encode(String password) {
        var passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public boolean isAdmin() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities()
                .stream()
                .anyMatch(r -> r.getAuthority().equals("ADMIN"));
    }

    public boolean isUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities()
                .stream()
                .anyMatch(r -> r.getAuthority().equals("USER"));
    }
}
