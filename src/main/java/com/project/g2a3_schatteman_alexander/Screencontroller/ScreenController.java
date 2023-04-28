package com.project.g2a3_schatteman_alexander.Screencontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ScreenController {

    @GetMapping("/BookDetailPage")
    public String showDetails() {
        return "BookDetailPage";
    }

    @GetMapping("/LibraryBooks")
    public String showLibrary() {
        return "LibraryBooks";
    }

    @GetMapping("/list-employees")
    public String showListEmployees() {
        return "list-employees";
    }


}
