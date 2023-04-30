package com.outfit.business.services;

import com.outfit.persistence.dao.UserRepository;
import com.outfit.persistence.model.Role;
import com.outfit.persistence.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Transactional
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            var appUser = userRepository.findByUsername(username);
            if (appUser == null) {
                System.out.println("User not found with the provided username" + appUser);
                return null;
            }
            return new CustomUserDetails(appUser, getAuthorities(appUser));
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
    }

    private String[] getRoles(User appUser) {
        List<String> roles = new ArrayList<>();
        for (Role role : appUser.getRoles()) {
            roles.add(role.getRole());
        }
        return Arrays.copyOf(roles.toArray(), roles.size(), String[].class);
    }

    private Set<GrantedAuthority> getAuthorities(User appUser) {
        var authorities = new HashSet<GrantedAuthority>();
        for (var role : appUser.getRoles()) {
            var grantedAuthority = new SimpleGrantedAuthority("ROLE_" + role.getRole());
            authorities.add(grantedAuthority);
        }
        return authorities;
    }
}