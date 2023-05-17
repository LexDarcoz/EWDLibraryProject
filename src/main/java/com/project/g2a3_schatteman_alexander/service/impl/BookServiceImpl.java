package com.project.g2a3_schatteman_alexander.service.impl;

import com.project.g2a3_schatteman_alexander.entities.Author;
import com.project.g2a3_schatteman_alexander.entities.Book;
import com.project.g2a3_schatteman_alexander.entities.Location;
import com.project.g2a3_schatteman_alexander.entities.User;
import com.project.g2a3_schatteman_alexander.repository.AuthorRepository;
import com.project.g2a3_schatteman_alexander.repository.BookRepository;
import com.project.g2a3_schatteman_alexander.repository.LocationRepository;
import com.project.g2a3_schatteman_alexander.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<Author> databaseAuthors = authorRepository.findAll();
        List<Location> databaseLocations = locationRepository.findAll();
        List<Author> authors = new ArrayList<>();
        List<Location> locations = new ArrayList<>();

        for (Author author : book.getAuthors()) {
            if (!author.getFirstname().isBlank() && !author.getLastname().isBlank()) {
                if (databaseAuthors.contains(author)) {
                    System.out.println("AUTHOR CONTAINS");
                    authors.add(databaseAuthors.get(databaseAuthors.indexOf(author)));
                } else {
                    authors.add(author);
                }
            }
        }
        for (Location location : book.getLocations()) {
            System.out.println(location.toString());
            if (!(location.getPlaceCode1() == 0 || location.getPlaceCode2() == 0 || location.getPlaceName().isBlank())) {
                if (databaseLocations.contains(location)) {
                    System.out.println("LOCATION CONTAINS");
                    locations.add(databaseLocations.get(databaseLocations.indexOf(location)));
                } else {
                    locations.add(location);
                }
            }
        }

        if (authors.isEmpty() || locations.isEmpty()) {
            throw new IllegalArgumentException("Author or location is empty");
        }
        Book newBook = new Book(book.getName(), authors, book.getISBNnumber(), book.getPrice(), 0, locations, new ArrayList<>());
        System.out.println(newBook);
        bookRepo.save(newBook);
    }


    @Override
    public List<Book> getTop10() {
        return bookRepo.findTop10ByOrderByStarsDescNameAsc();
    }

    @Override
    public Book getByISBN(String isbn) {
        Book book = bookRepo.findByISBNnumber(isbn);
        return book;
    }

    @Override
    public void deleteByBookId(long id) {
        bookRepo.deleteById(id);
    }

    @Override
    public List<Book> getByAuthor(Author author) {
        return bookRepo.findBooksByAuthors(author);
    }

    @Override
    public List<Book> getFavorites(User user) {
        return bookRepo.findBookByUsers(user);
    }


}
