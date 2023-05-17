package com.project.g2a3_schatteman_alexander.controller;

import com.project.g2a3_schatteman_alexander.entities.Book;
import com.project.g2a3_schatteman_alexander.entities.User;
import com.project.g2a3_schatteman_alexander.service.UserService;
import com.project.g2a3_schatteman_alexander.validation.Registration;
import com.project.g2a3_schatteman_alexander.validation.RegistrationValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private RegistrationValidation validator;
    @Autowired
    private UserService userService;


    @PostMapping("/new")
    public String addNewUser(@Valid Registration registration, BindingResult result, Model model) {
        System.out.println("adding new user");
        validator.validate(registration, result);
        if (result.hasErrors()) {
            return "register";
        }

        User user = new User(registration.getFirstName(), registration.getLastName(), registration.getEmail(), registration.getPassword());
     

        userService.addUser(user);
        return "redirect:/login";
    }

    @PostMapping("/favorite/add/{id}")
    public String addFavorite(@PathVariable("id") Long id, Model model) {
        Book response = userService.addFavorite(id);
        User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("book", response);
        model.addAttribute("user", user);
        model.addAttribute("status", "added");
        return "redirect:/library/" + id + "?status=added";
    }

    @PostMapping("/favorite/remove/{id}")
    public String removeFavorite(@PathVariable("id") Long id, Model model) {
        Book response = userService.removeFavorite(id);
        User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("book", response);
        model.addAttribute("user", user);
        return "redirect:/library/" + id + "?status=removed";
    }
}

