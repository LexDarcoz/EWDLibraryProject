package com.project.g2a3_schatteman_alexander.service;

import com.project.g2a3_schatteman_alexander.entities.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class RestService {

    private final WebClient client;

    public RestService(WebClient.Builder builder) {
        this.client = builder.baseUrl("http://localhost:8080/api").build();
    }

    public ResponseEntity<Book> getBookByIsbn(String isbn) {
        return client.get().uri("/books/{isbn}", isbn).retrieve().toEntity(Book.class).block();
    }

    public ResponseEntity<List<Book>> getBooksByAuthor(long id) {
        return client.get().uri("/authors/{id}", id).retrieve().toEntityList(Book.class).block();
    }
}