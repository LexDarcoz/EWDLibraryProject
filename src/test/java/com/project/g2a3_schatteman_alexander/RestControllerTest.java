package com.project.g2a3_schatteman_alexander;

import com.project.g2a3_schatteman_alexander.controller.RestController;
import com.project.g2a3_schatteman_alexander.entities.Author;
import com.project.g2a3_schatteman_alexander.entities.Book;
import com.project.g2a3_schatteman_alexander.repository.AuthorRepository;
import com.project.g2a3_schatteman_alexander.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
public class RestControllerTest {
    @Mock
    private BookService mock;

    @Mock
    private AuthorRepository authorRepository;

    private RestController restController;


    private MockMvc mockMvc;

    private void performRest(String uri) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(status().isOk());
    }

    @Test
    public void test_GetBookByIsbn() throws Exception {
        MockitoAnnotations.openMocks(this);
        restController = new RestController();
        mockMvc = standaloneSetup(restController).build();
        ReflectionTestUtils.setField(restController, "service", mock);

        Book book = new Book("Advanced Java Programming", null, "9876543210", 39.99, 10, null, null);
        book.setId((long) 20);

        Mockito.when(mock.getByISBN("9876543210")).thenReturn(book);

        performRest("/api/books/9876543210");

        Mockito.verify(mock).getByISBN("9876543210");
    }

    @Test
    public void test_GetBooksByAuthors() throws Exception {
        MockitoAnnotations.openMocks(this);
        restController = new RestController();
        mockMvc = standaloneSetup(restController).build();
        ReflectionTestUtils.setField(restController, "service", mock);
        ReflectionTestUtils.setField(restController, "authorRepository", authorRepository);

        Author author = new Author("John", "Doe");
        author.setId((long) 10);
        // Create a list of authors
        ArrayList<Author> authorList = new ArrayList<>();
        authorList.add(author);

        Book book = new Book("Python Fundamentals", authorList, "5432109876", 29.99, 8, null, null);
        book.setId((long) 15);

        List<Book> bookList = new ArrayList<>();
        bookList.add(book);

        Mockito.when(authorRepository.findById((long) 10)).thenReturn(java.util.Optional.of(author));

        Mockito.when(mock.getByAuthor(author)).thenReturn(bookList);

        performRest("/api/authors/10");

        Mockito.verify(authorRepository).findById((long) 10);

        Mockito.verify(mock).getByAuthor(author);
    }
}
