package com.project.g2a3_schatteman_alexander.service.impl;

import com.project.g2a3_schatteman_alexander.config.AuthUserDetails;
import com.project.g2a3_schatteman_alexander.entities.User;
import com.project.g2a3_schatteman_alexander.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AuthUserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
//        System.out.println(user);
        return new AuthUserDetails(user);
    }
}
