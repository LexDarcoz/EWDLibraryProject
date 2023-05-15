package com.project.g2a3_schatteman_alexander.service;

import com.project.g2a3_schatteman_alexander.entities.Book;
import com.project.g2a3_schatteman_alexander.entities.User;

public interface UserService {


    String addUser(User user);

    User getUserByEmail(String email);

    Book addFavorite(Long id);

    Book removeFavorite(Long id);

}
