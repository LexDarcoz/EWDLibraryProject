package com.project.g2a3_schatteman_alexander.repository;

import com.project.g2a3_schatteman_alexander.entities.Author;
import com.project.g2a3_schatteman_alexander.entities.Book;
import com.project.g2a3_schatteman_alexander.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findBookByAuthors(Author author);

    List<Book> findTop10ByOrderByStarsDescNameDesc();

    Book findByISBNnumber(String isbn);

    List<Book> findBookByUsers(User user);

}
