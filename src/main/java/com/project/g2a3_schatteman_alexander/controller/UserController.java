package com.project.g2a3_schatteman_alexander.controller;

import com.project.g2a3_schatteman_alexander.entities.Book;
import com.project.g2a3_schatteman_alexander.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.Registration;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RegistrationValidation validator;
    @Autowired
    private UserService userService;


    @PostMapping("/new")
    public String addNewUser(@Validated Registration registration, BindingResult result, Model model) {
        System.out.println("adding new user");
        validator.validate(registration, result);
        if (result.hasErrors()) {
            return "register";
        }

        User user = new User();
        user.setFirstname(registration.getFirstName());
        user.setLastname(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(registration.getPassword());

        userService.addUser(user);
        return "redirect:/login";
    }
    @PostMapping("/favorite/add/{id}")
    public String addFavorite(@PathVariable("id") Long id, Model model) {
        System.out.println("add favorite");
        Book response = userService.addFavorite(id);
        System.out.println("returning");

        System.err.println("bad request");

        User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()) ;
        model.addAttribute("book", response);
        model.addAttribute("user", user);
        model.addAttribute("status", "added");
        return "redirect:/books/detail/" + id+"?status=added";
    }

    @PostMapping("/favorite/remove/{id}")
    public String removeFavorite(@PathVariable("id") Long id, Model model) {
        System.out.println("remove favorite");
        Book response = userService.removeFavorite(id);
        System.out.println("returning");
//        if (!response) {
        System.err.println("bad request");
//            return ResponseEntity.badRequest().build();
//        }
        User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()) ;
        model.addAttribute("book", response);
        model.addAttribute("user", user);
        return "redirect:/books/detail/" + id+"?status=removed";
    }
}
