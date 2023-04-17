package com.project.g2a3_schatteman_alexander.Screencontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ScreenController {

    @GetMapping("/bookdetailpage")
    public String showDetails() {
        return "bookdetailpage";
    }

    @GetMapping("/LibraryBooks")
    public String showLibrary() {
        return "LibraryBooks";
    }
}