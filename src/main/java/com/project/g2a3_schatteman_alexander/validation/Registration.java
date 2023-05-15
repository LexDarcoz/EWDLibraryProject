package com.project.g2a3_schatteman_alexander.validation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Registration {
    @NotBlank(message = "{validation.email.notBlank}")
    @Email(message = "{validation.email.invalid}")
    private String email;

    @NotBlank(message = "{validation.firstname.NotBlank.message}")
    private String firstName;

    @NotBlank(message = "{validation.lastName.notBlank}")
    private String lastName;

    @Size(min = 4, max = 20, message = "{validation.password.size}")
    private String password;

    @NotBlank(message = "{validation.confirmPassword.notBlank}")
    private String confirmPassword;



    public Registration() {
    }
}