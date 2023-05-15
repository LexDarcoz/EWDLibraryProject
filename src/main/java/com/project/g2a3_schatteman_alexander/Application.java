package com.project.g2a3_schatteman_alexander;

import com.project.g2a3_schatteman_alexander.validation.BookValidation;
import com.project.g2a3_schatteman_alexander.validation.RegistrationValidation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
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

