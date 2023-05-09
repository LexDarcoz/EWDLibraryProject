package com.project.g2a3_schatteman_alexander.service;

import com.project.g2a3_schatteman_alexander.entities.Author;
import com.project.g2a3_schatteman_alexander.entities.Book;

import java.util.List;


public interface BookService {

    List<Book> getALL();

    Book getBookById(Integer id);

    void addBook(Book book);

    void updateBookById(long id);

    void deleteBookById(long id);

    List<Book> getByAuthor(Author author);
}
