package com.project.g2a3_schatteman_alexander.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@Table(name = "tbl_books", uniqueConstraints = @UniqueConstraint(columnNames = {"ISBNnumber"}))
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "{validation.book.name.notBlank}")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Author> authors;

    @Column(name = "ISBNnumber", unique = true)
    @NotBlank(message = "{validation.book.isbn.notBlank}")
    private String ISBNnumber;

    @Column(name = "price")
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    @DecimalMax(value = "99.99", message = "{validation.book.price.decimalMax}")
    private double price;

    @Column(name = "rating")
    private int rating;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Location> locations;

    @ManyToMany(mappedBy = "books", cascade = CascadeType.ALL)
    private List<User> users;


    public Book(String name, List<Author> authors, String ISBNnumber, double price, int rating, List<Location> locations, List<User> users) {
        this.name = name;
        this.authors = authors;
        this.ISBNnumber = ISBNnumber;
        this.price = price;
        this.rating = rating;
        this.locations = locations;
        this.users = users;
    }
    public Book() {
    }

    public Long getId() {
        return id;
    }

    //generate getters for all attributes
    public String getName() {
        return name;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public String getISBNnumber() {
        return ISBNnumber;
    }

    public double getPrice() {
        return price;
    }

    public int getRating() {
        return rating;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    //generate setters for all attributes
    public void setName(String name) {
        this.name = name;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void setISBNnumber(String ISBNnumber) {
        this.ISBNnumber = ISBNnumber;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStarRating(int rating) {
        this.rating = rating;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authors=" + authors +
                ", ISBNnumber='" + ISBNnumber + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", locations=" + locations +
                '}';
    }


}
