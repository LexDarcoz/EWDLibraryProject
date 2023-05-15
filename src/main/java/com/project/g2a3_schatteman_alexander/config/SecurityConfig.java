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
                .authorizeHttpRequests().requestMatchers("/*", "/books/*", "/user/new", "/api/**").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/resources/**").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/login/**").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/register/**").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/books/detail/*", "/books/favorite", "/user/favorite/*").hasAuthority("USER")
                .and()
                .authorizeHttpRequests().requestMatchers("/book/new", "/books/new").hasAuthority("ADMIN")
                .and()
                .authorizeHttpRequests().requestMatchers("/user/favorite/add/*", "/user/favorite/remove/*").hasAuthority("USER")
                .and().formLogin()
                .defaultSuccessUrl("/books/1")
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