package com.project.g2a3_schatteman_alexander.seeds;

import com.project.g2a3_schatteman_alexander.entities.Author;
import com.project.g2a3_schatteman_alexander.entities.Book;
import com.project.g2a3_schatteman_alexander.entities.Location;
import com.project.g2a3_schatteman_alexander.entities.User;
import com.project.g2a3_schatteman_alexander.repository.AuthorRepository;
import com.project.g2a3_schatteman_alexander.repository.BookRepository;
import com.project.g2a3_schatteman_alexander.repository.LocationRepository;
import com.project.g2a3_schatteman_alexander.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitDataConfig implements CommandLineRunner {

    @Autowired
    private LocationRepository locationRepo;

    @Autowired
    private AuthorRepository authorRepo;

    @Autowired
    private BookRepository repo;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {

        //Locations
        locationRepo.save(new Location(50, 300, "Library A"));
        locationRepo.save(new Location(100, 290, "Library B"));
        locationRepo.save(new Location(200, 280, "Library C"));
        locationRepo.save(new Location(300, 400, "Library D"));

        //Authors
        authorRepo.save(new Author("Stephen King", "Horror"));
        authorRepo.save(new Author("J.K. Rowling", "Fantasy"));
        authorRepo.save(new Author("George R.R. Martin", "Fantasy"));
        authorRepo.save(new Author("Harper Lee", "Fiction"));

        //Add books
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Dan Brown", "Thriller"));
        List<Location> locations = new ArrayList<>();
        locations.add(new Location(40, 260, "Boekentoren"));
        Book book = new Book("The Da Vinci Code", authors, "9781400079179", 15.99, 5, locations, new ArrayList<>());
        repo.save(book);

        List<Author> authors1 = new ArrayList<>();
        authors1.add(new Author("Agatha Christie", "Mystery"));
        authors1.add(new Author("Arthur Conan Doyle", "Mystery"));
        List<Location> locations1 = new ArrayList<>();
        locations1.add(new Location(50, 250, "Library F"));
        Book book1 = new Book("Murder on the Orient Express", authors1, "9780062073501", 11.99, 3, locations1, new ArrayList<>());
        repo.save(book1);

        List<Author> authors2 = new ArrayList<>();
        authors2.add(new Author("Gabriel García Márquez", "Magical Realism"));
        List<Location> locations2 = new ArrayList<>();
        locations2.add(new Location(60, 240, "Library G"));
        Book book2 = new Book("One Hundred Years of Solitude", authors2, "9780060883287", 14.99, 8, locations2, new ArrayList<>());
        repo.save(book2);

        for (int i = 1; i <= 20; i++) {
            List<Author> authorsList = new ArrayList<>();
            authorsList.add(new Author("Author " + i, "Genre " + i));
            List<Location> locationsList = new ArrayList<>();
            locations.add(new Location(70 - i, 230 + i, "Library " + (char) ('H' + i)));
            Book books = new Book("Book " + i, authorsList, "ISBN" + i, 9.99, i % 5 + 1, locationsList, new ArrayList<>());
            repo.save(books);
        }

        //add users
        List<Book> books = new ArrayList<>();
        books.add(new Book("To Kill a Mockingbird", null, "9780060935467", 12.99, 5, null, null));
        List<Book> emptybooks = new ArrayList<>();
        User user = new User("Alexander", "Schatteman", "PWD", "Alexander@gmail.com", "USER,ADMIN", books, 20);
        User user2 = new User("TwoFavBooks", "Schatteman", "PWD", "twofav@gmail.com", "USER", emptybooks, 2);
        User user3 = new User("Normal User", "Schatteman", "PWD", "normal@gmail.com", "USER", emptybooks, 20);

        userService.addUser(user);
        userService.addUser(user2);
        userService.addUser(user3);
    }
}