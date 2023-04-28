package com.project.g2a3_schatteman_alexander.controller;

import com.project.g2a3_schatteman_alexander.entities.Employee;
import com.project.g2a3_schatteman_alexander.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {

    private EmployeeRepository eRepo;

    @GetMapping("/showEmployees")
    public ModelAndView showsEmployees() {
        ModelAndView mav = new ModelAndView("list-employees");
        List<Employee> list = eRepo.findAll();
        mav.addObject("employees", list);
        return mav;
    }
}
