package com.project.g2a3_schatteman_alexander;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {


    @Bean
    public RegistrationValidation registrationValidation() {
        return new RegistrationValidation();
    }

    @Bean
    public BookValidator bookValidator() {
        return new BookValidator();
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}

