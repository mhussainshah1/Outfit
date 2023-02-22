package com.outfit.web.config;

import com.outfit.business.entities.repositories.UserRepository;
import com.outfit.business.services.SSUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private SSUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/",
                        "/h2/**",
                        "/termsandconditions",
                        "/register",
                        "/css/**",
                        "/js/**",
                        "/img/**",
                        "/detail/{id}",
                        "/detailcategory/{id}",
                        "/detailclimate/{id}",
                        "/detailoccasion/{id}",
                        "/detailwind/{id}",
                        "/search",
                        "/about").permitAll()
//                .access("hasAnyAuthority('USER','ADMIN')")
                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")//.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout").permitAll()
                .and()
                .httpBasic();
        http
                .csrf().disable();
        http
                .headers().frameOptions().disable();
        return http.build();
    }

/*    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/css/**", "/js/**", "/img/**");
    }*/

    @Bean
    public AuthenticationProvider userDetailsService(BCryptPasswordEncoder passwordEncoder) {
        userDetailsService = new SSUserDetailsService(userRepository);
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}
