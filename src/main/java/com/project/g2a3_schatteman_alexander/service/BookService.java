package com.project.g2a3_schatteman_alexander.service;

import com.project.g2a3_schatteman_alexander.entities.Author;
import com.project.g2a3_schatteman_alexander.entities.Book;
import com.project.g2a3_schatteman_alexander.entities.User;

import java.util.List;


public interface BookService {

    List<Book> getAll();

    Book getBookById(Long id);

    List<Book> getTop10();

    void addBook(Book book);

    Book getByISBN(String isbn);

    void deleteByBookId(long id);

    List<Book> getByAuthor(Author author);

    List<Book> getFavorites(User user);
}
