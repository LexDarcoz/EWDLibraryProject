package com.project.g2a3_schatteman_alexander.config;

import com.project.g2a3_schatteman_alexander.service.impl.AuthUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new AuthUserService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests().requestMatchers("/library", "/library/*", "/user/new", "/library/favorite", "/user/favorite/*", "/user/favorite/add/*", "/user/favorite/remove/*", "/api/**, ").hasAuthority("USER")
                .and()
                .authorizeHttpRequests().requestMatchers("/components/**", "/*").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/login/**").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/register/**").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/library/addBook", "/library/delete/*").hasAuthority("ADMIN")
                .and().formLogin()
                .defaultSuccessUrl("/")
//                .failureUrl("/error")
                .permitAll()
                .and().logout().permitAll()
                .and()
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}