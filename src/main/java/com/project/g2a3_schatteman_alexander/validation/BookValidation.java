package com.project.g2a3_schatteman_alexander.validation;


import com.project.g2a3_schatteman_alexander.entities.Author;
import com.project.g2a3_schatteman_alexander.entities.Book;
import com.project.g2a3_schatteman_alexander.entities.Location;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BookValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;
        if (book.getAuthors().isEmpty()) {
            errors.rejectValue("authors", "author.empty", "There must be at least 1 author!");
        } else {
            Author author = book.getAuthors().get(0);
            if (author.getFirstname().isBlank() && author.getLastname().isBlank()) {
                errors.rejectValue("authors", "author.primaryEmpty", "There must be a primary author!");
            }
        }
        if (book.getISBNnumber().isEmpty()) {
            errors.rejectValue("ISBNnumber", "isbn.empty", "ISBN cannot be empty");
        } else {
            String ISBNnumber = book.getISBNnumber();
            ISBNnumber = ISBNnumber.replaceAll("-", "").trim();
            System.out.println(ISBNnumber);
            if (ISBNnumber.length() != 13 && ISBNnumber.length() != 10) {
                errors.rejectValue("ISBNnumber", "isbn.length", "ISBN must be 10 or 13 characters long");
            }
            if (!validateISBN(ISBNnumber)) {
                errors.rejectValue("ISBNnumber", "isbn.invalid", "This ISBN is not valid");
            }
        }
        if (book.getLocations().isEmpty()) {
            errors.rejectValue("locations", "location.empty", "There must be at least 1 location!");
        } else {
            if (book.getLocations().get(0).getPlaceCode1() == 0 || book.getLocations().get(0).getPlaceCode2() == 0 || book.getLocations().get(0).getPlaceName().isBlank()) {
                errors.rejectValue("locations", "location.primaryEmpty", "There must be a primary location!");
            }
            for (Location location : book.getLocations()) {
                if (!(location.getPlaceCode1() == 0 || location.getPlaceCode2() == 0)) {
                    if (location.getPlaceCode1() < 50 || location.getPlaceCode2() < 50) {
                        errors.rejectValue("locations", "location.code", "The place codes must be greater than 50!");
                    }
                    int result = Math.abs(location.getPlaceCode1() - location.getPlaceCode2());
                    if (result < 50) {
                        errors.rejectValue("locations", "location.distance", "The distance between the 2 place codes must be at least 50");
                    }
                    if (!location.getPlaceName().matches("[A-Za-z]+")) {
                        errors.rejectValue("locations", "location.name", "The place name must consist of letters only!");
                    }
                }
            }
        }
    }

    private boolean validateISBN(String isbn) {
        int sum = 0;

        if (isbn.length() == 10) {
            // For a 10-digit ISBN, multiply each digit by its position and sum the results
            for (int i = 0; i < 10; i++) {
                char c = isbn.charAt(i);
                if (i == 9 && c == 'X') {
                    // Handle the case where the last digit is 'X'
                    sum += 10;
                } else if (Character.isDigit(c)) {
                    sum += (10 - i) * (c - '0');
                } else {
                    // Invalid character
                    return false;
                }
            }
        } else {
            if (!isbn.matches("[0-9]+")) {
                return false;
            }
            // For a 13-digit ISBN, multiply each digit by either 1 or 3 (alternating) and sum the results
            for (int i = 0; i < 13; i++) {
                char c = isbn.charAt(i);
                if (i % 2 == 0) {
                    sum += (c - '0');
                } else {
                    sum += 3 * (c - '0');
                }
            }
        }

        System.out.println(sum);

        return (sum % 10 == 0);
    }
}
