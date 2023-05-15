package com.project.g2a3_schatteman_alexander.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "firstname")
    @NotBlank
    @Size(min = 3, max = 60)
    private String firstname;

    @Column(name = "lastname")
    @NotBlank
    @Size(min = 3, max = 60)
    private String lastname;

    @Column(name = "password")
    private String password;
    @Column(name = "email")
    @NotBlank
    private String email;

    @Column(name = "role")
    private String role;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Book> books;

    private int maxFavorites;

    public User(String firstname, String lastname, String password, String email, String role, List<Book> books, int maxFavorites) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.role = role;
        this.books = books;
        setMaxFavorites(maxFavorites);
    }

    public User(String firstname, String lastname, String password, String email) {
        this(firstname, lastname, password, email, "USER", new ArrayList<>(), 10);
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

//    public String getSalt() {
//        return salt;
//    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getMaxFavorites() {
        return maxFavorites;
    }

    private void setMaxFavorites(int maxFavorites) {
        this.maxFavorites = maxFavorites;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public void removeBook(Book book) {
        this.books.remove(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", books=" + books +
                '}';
    }
}
