package com.project.g2a3_schatteman_alexander;

import com.project.g2a3_schatteman_alexander.controller.BookController;
import com.project.g2a3_schatteman_alexander.entities.Author;
import com.project.g2a3_schatteman_alexander.entities.Book;
import com.project.g2a3_schatteman_alexander.entities.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

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


