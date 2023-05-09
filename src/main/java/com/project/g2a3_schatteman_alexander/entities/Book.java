package com.project.g2a3_schatteman_alexander.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tbl_books")
//@Table(name = "tbl_books", uniqueConstraints = @UniqueConstraint(columnNames = {"ISBNnumber"}))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name")
    private String name;

    @Column(name = "iSBN_number")
    private Long iSBN_number;


    @Column(name = "price")
    private double price;

    @Column(name = "stars")
    private int stars;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Author> authors;

    @ManyToMany(mappedBy = "books", cascade = CascadeType.ALL)
    private List<User> users;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Location> location;


}
