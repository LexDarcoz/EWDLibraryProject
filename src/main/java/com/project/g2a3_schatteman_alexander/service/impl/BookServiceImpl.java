package com.project.g2a3_schatteman_alexander.service.impl;

import com.project.g2a3_schatteman_alexander.entities.Author;
import com.project.g2a3_schatteman_alexander.entities.Book;
import com.project.g2a3_schatteman_alexander.entities.User;
import com.project.g2a3_schatteman_alexander.repository.AuthorRepository;
import com.project.g2a3_schatteman_alexander.repository.BookRepository;
import com.project.g2a3_schatteman_alexander.repository.LocationRepository;
import com.project.g2a3_schatteman_alexander.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepo;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Book> getAll() {
        return (List<Book>) bookRepo.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepo.findById(id).orElseThrow();
    }

    @Override
    public void addBook(Book book) {

    }


    @Override
    public Book getByISBN(String isbn) {
        Book book = bookRepo.findByISBNnumber(isbn);
        return book;
    }

    @Override
    public void updateBookById(long id) {

    }

    @Override
    public void deleteBookById(long id) {

    }

    @Override
    public List<Book> getByAuthor(Author author) {
        return null;
    }

    @Override
    public List<Book> getFavorites(User user) {
        return bookRepo.findBookByUsers(user);
    }


}
