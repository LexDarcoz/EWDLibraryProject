package com.project.g2a3_schatteman_alexander;

import com.project.g2a3_schatteman_alexander.controller.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private BookController bookController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() throws Exception {
        assertThat(bookController).isNotNull();
    }

//    @WithMockUser(username = "random", authorities = {"USER"})
//    @Test
//    public void testShowBooks() throws Exception {
//        List<Author> authors2 = new ArrayList<>();
//        authors2.add(new Author("Gabriel García Márquez", "Magical Realism"));
//        List<Location> locations2 = new ArrayList<>();
//        locations2.add(new Location(60, 240, "Library G"));
//        Book book2 = new Book("One Hundred Years of Solitude", authors2, "9780060883287", 14.99, 8, locations2, new ArrayList<>());
//        book2.setId((long) 1);
//        List<Book> books = new ArrayList<>();
//        books.add(book2);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/library"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("books/libraryBooks"))
//                .andExpect(model().attributeExists("bookList"));
//    }


    @WithMockUser(username = "random", authorities = {"USER"})
    @Test
    public void testShowBookById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/library/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/bookDetailPage"))
                .andExpect(model().attributeExists("books"));
//                .andExpect(model().attribute("book", book));
    }
}


