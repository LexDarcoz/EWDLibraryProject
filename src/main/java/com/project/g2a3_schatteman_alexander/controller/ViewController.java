package com.project.g2a3_schatteman_alexander.controller;

import com.project.g2a3_schatteman_alexander.validation.Registration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ViewController {

    @GetMapping("/")
    public String showHome() {
        return "redirect:/library";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        Registration registration = new Registration();
        model.addAttribute("registration", registration);
        return "register";
    }
}
