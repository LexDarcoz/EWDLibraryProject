package com.project.g2a3_schatteman_alexander.controller;

import com.project.g2a3_schatteman_alexander.entities.Book;
import com.project.g2a3_schatteman_alexander.entities.User;
import com.project.g2a3_schatteman_alexander.service.BookService;
import com.project.g2a3_schatteman_alexander.service.UserService;
import com.project.g2a3_schatteman_alexander.validation.BookValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    List<Book> books = new ArrayList<>();
    @Autowired
    private UserService userService;
    @Autowired
    private BookValidation validator;
    @Autowired
    private BookService service;

    @GetMapping("/library")
    public ModelAndView showBooksByPage() {
        books = service.getAll();
        ModelAndView mav = new ModelAndView("books/LibraryBooks", "bookList", books);
        return mav;
    }

    @GetMapping("/{id}")
    public String showBookById(@PathVariable Long id, Model model) {
        User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Book book = service.getBookById(id);
        if (book == null) {
            return "redirect:/error";
        }
        model.addAttribute("book", book);
        model.addAttribute("user", user);
        return "bookDetails";
    }

    @GetMapping
    public String getBooks(Model model) {
//        model.addAttribute("bookList", service.getAll());
        return "redirect:/books/1";
    }

    @PostMapping("/new")
    public String onSubmit(@Valid Book book, BindingResult result, Model model) {
        validator.validate(book, result);
        if (result.hasErrors()) {
            return "newBook";
        }
        service.addBook(book);
        return "redirect:/books/1";
    }


    @GetMapping("/favorite")
    public String getFavorites(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.getUserByEmail(authentication.getName());
        System.out.println(currentUser);
        List<Book> books = service.getFavorites(currentUser);
        System.out.println(books);
        System.out.println(currentUser.getBooks());
        model.addAttribute("books", books);
        model.addAttribute("user", currentUser);
        return "favorites";
    }
}