package com.project.g2a3_schatteman_alexander.controller;

import com.project.g2a3_schatteman_alexander.entities.Book;
import com.project.g2a3_schatteman_alexander.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping({"/", "/list"})
    public ModelAndView showEmployees() {
        ModelAndView mav = new ModelAndView("landingPage");
        List<Book> list = bookService.getALL();
        mav.addObject("books", list);
        return mav;
    }

    @GetMapping("/addBook")
    public ModelAndView addEmployeeForm() {
        ModelAndView mav = new ModelAndView("books/addBook");
        Book newBook = new Book();
        mav.addObject("book", newBook);
        return mav;
    }


}
