package com.outfit.web.config;

import com.outfit.business.services.CustomOAuth2UserService;
import com.outfit.business.services.CustomUserDetailsService;
import com.outfit.business.services.OAuth2LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private CustomOAuth2UserService oAuth2UserService;

    @Autowired
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @Autowired
    public SecurityConfiguration(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz
                        .requestMatchers("/**",
                                "/resources/**",
                                "/oauth2/**",
                                "/h2/**",
                                "/termsandconditions",
                                "/register",
                                "/css/**",
                                "/js/**",
                                "/img/**",
                                "/error",
                                "/webjars/**",
                                "/detail/{id}",
                                "/detailcategory/{id}",
                                "/detailclimate/{id}",
                                "/detailoccasion/{id}",
                                "/detailwind/{id}",
                                "/search",
                                "/about").permitAll()//.hasAnyRole("USER","ADMIN")
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated())
//                .userDetailsService(userDetailsService)
                .formLogin(login -> login
                        .loginPage("/login")
                        .permitAll())
                .oauth2Login(login -> login
                        .loginPage("/login")
                        .userInfoEndpoint(e -> e.userService(oAuth2UserService))
                        .successHandler(oAuth2LoginSuccessHandler))
                .logout(logout -> logout
                        .logoutUrl("/logout")//.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout").permitAll())
                .httpBasic(Customizer.withDefaults());
        http
                .csrf(CsrfConfigurer::disable)
                .headers(headers -> headers
                        .frameOptions()
                        .disable());
        return http.build();
    }

/*    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/resources/**", "/error", "/webjars/**");
    }*/

   /* @Bean
    public AuthenticationProvider userDetailsService(BCryptPasswordEncoder passwordEncoder) {
        userDetailsService = new SSUserDetailsService(userRepository);
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }*/

    @Bean
    public AuthenticationManager userDetailsService(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }
}
