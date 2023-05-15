package com.project.g2a3_schatteman_alexander.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "location")
public class Location implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "placeCode1")
    private int placeCode1;

    @Column(name = "placeCode2")
    private int placeCode2;

    @Column(name = "placeName")
    private String placeName;

    @ManyToOne(cascade = CascadeType.ALL)
    private Book book;


    public Location(int placeCode1, int placeCode2, String placeName) {
        this.placeCode1 = placeCode1;
        this.placeCode2 = placeCode2;
        this.placeName = placeName;
    }

    public Location() {
    }

    public long getId() {
        return id;
    }

    public int getPlaceCode1() {
        return placeCode1;
    }

    public int getPlaceCode2() {
        return placeCode2;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceCode1(int placeCode1) {
        this.placeCode1 = placeCode1;
    }

    public void setPlaceCode2(int placeCode2) {
        this.placeCode2 = placeCode2;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location location)) return false;

        if (!Objects.equals(placeCode1, location.placeCode1)) return false;
        if (!Objects.equals(placeCode2, location.placeCode2)) return false;
        return Objects.equals(placeName, location.placeName);
    }

    @Override
    public String toString() {
        return "Location{" +
                ", placeCode1=" + placeCode1 +
                ", placeCode2=" + placeCode2 +
                ", placeName='" + placeName  +
                '}';
    }
}
