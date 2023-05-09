package com.project.g2a3_schatteman_alexander.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_location")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "plaatscode1")
    private int plaatsCode1;
    @Column(name = "plaatsCode2")
    private int plaatsCode2;

    @Column(name = "plaatsNaam")
    private String plaatsNaam;

    @ManyToOne(cascade = CascadeType.ALL)
    private Book book;

    public int getId() {
        return id;
    }

    public int getPlaatsCode1() {
        return plaatsCode1;
    }

    public int getPlaatsCode2() {
        return plaatsCode2;
    }

    public String getPlaatsNaam() {
        return plaatsNaam;
    }

    public Book getBook() {
        return book;
    }
}
