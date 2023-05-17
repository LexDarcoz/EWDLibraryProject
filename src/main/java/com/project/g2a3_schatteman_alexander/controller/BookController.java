package com.project.g2a3_schatteman_alexander.controller;

import com.project.g2a3_schatteman_alexander.entities.Author;
import com.project.g2a3_schatteman_alexander.entities.Book;
import com.project.g2a3_schatteman_alexander.entities.Location;
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
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping({"/library"})
    public ModelAndView showBooks() {
        books = service.getAll();
        ModelAndView mav = new ModelAndView("books/libraryBooks", "bookList", books);
        return mav;
    }

    @GetMapping("/library/delete/{id}")
    public String deleteBookById(@PathVariable(value = "id") long bookId) {
        service.deleteByBookId(bookId);
        return "redirect:/library";
    }

    @GetMapping("/library/{id}")
    public ModelAndView showBookById(@PathVariable Long id) {
        Book book = service.getBookById(id);
        User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        ModelAndView mav = new ModelAndView();
        if (book == null) {
            mav.setViewName("error");
            return mav;
        }


        mav.addObject("user", user);
        mav.addObject("books", book);
        mav.setViewName("books/bookDetailPage");
        return mav;
    }

    @PostMapping("/library/newBook")
    public String onSubmit(@Valid Book book, BindingResult result, Model model) {
        validator.validate(book, result);
        if (result.hasErrors()) {
            return "books/addBook";
        }
        System.out.println(book);
        System.out.println(book.getAuthors());
        System.out.println(book.getAuthors().get(0).getFirstname());
        System.out.println(book.getLocations().get(0).getPlaceName());
        service.addBook(book);
        return "redirect:/";
    }

    @GetMapping("/top10")
    public ModelAndView getTop10() {
        List<Book> book = service.getTop10();

        ModelAndView mav = new ModelAndView();
        mav.addObject("books", book);
        mav.setViewName("books/Top10Books");
        return mav;
    }

    @RequestMapping("/library/addBook")
    public String showForm(Model model) {
        Book book = new Book();
        List<Author> authors = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            authors.add(new Author());
        }

        book.setAuthors(authors);

        List<Location> locations = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            locations.add(new Location());
        }

        book.setLocations(locations);
        model.addAttribute("book", book);
        return "books/addBook";
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