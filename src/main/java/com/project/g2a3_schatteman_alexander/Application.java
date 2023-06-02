package com.project.g2a3_schatteman_alexander;

import com.project.g2a3_schatteman_alexander.entities.Book;
import com.project.g2a3_schatteman_alexander.service.RestService;
import com.project.g2a3_schatteman_alexander.validation.BookValidation;
import com.project.g2a3_schatteman_alexander.validation.RegistrationValidation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        RestService restService = context.getBean(RestService.class);
        System.out.println(restService.getBookByIsbn("9781400079179").toString());
        for (Book book : restService.getBooksByAuthor(5).getBody()) {
            System.out.println(book.toString());
        }
    }

    @Bean
    public RegistrationValidation registrationValidation() {
        return new RegistrationValidation();
    }

    @Bean
    public BookValidation bookValidator() {
        return new BookValidation();
    }


}

