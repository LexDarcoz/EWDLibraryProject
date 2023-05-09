package com.project.g2a3_schatteman_alexander.controller;


import com.project.g2a3_schatteman_alexander.entities.Employee;
import com.project.g2a3_schatteman_alexander.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class EmployeeController {
    @Autowired
    private EmployeeRepository eRepo;


    @GetMapping({"/showEmployees", "/list"})
    public ModelAndView showEmployees() {
        ModelAndView mav = new ModelAndView("employees/list-employees");
        List<Employee> list = eRepo.findAll();
        mav.addObject("employees", list);
        return mav;

    }

    @GetMapping("/addEmployeeForm")
    public ModelAndView addEmployeeForm() {
        ModelAndView mav = new ModelAndView("employees/add-employee-form");
        Employee newEmployee = new Employee();
        mav.addObject("employee", newEmployee);
        return mav;
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long employeeId) {
        ModelAndView mav = new ModelAndView("employees/add-employee-form");
        System.out.println("aaaaaaaaaaaaaaaaaaaa");
        Employee employee = eRepo.getReferenceById(employeeId);
        mav.addObject("employee", employee);
        return mav;
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam long employeeId) {
        eRepo.deleteById(employeeId);
        return "redirect:/list";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee) {
        eRepo.save(employee);
        return "redirect:/list";
    }


}
