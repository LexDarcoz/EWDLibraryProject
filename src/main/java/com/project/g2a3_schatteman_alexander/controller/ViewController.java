package com.project.g2a3_schatteman_alexander.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ViewController {
    @RequestMapping("/")
    public String showHome() {
        return "redirect:/library";
    }


}
