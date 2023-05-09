package com.project.g2a3_schatteman_alexander.service.impl;

import com.project.g2a3_schatteman_alexander.entities.Author;
import com.project.g2a3_schatteman_alexander.entities.Book;
import com.project.g2a3_schatteman_alexander.repository.BookRepository;
import com.project.g2a3_schatteman_alexander.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepo;

    @Override
    public List<Book> getALL() {
        return (List<Book>) bookRepo.findAll();
    }

    @Override
    public Book getBookById(Integer id) {
        return null;
    }

    @Override
    public void addBook(Book book) {

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
}
