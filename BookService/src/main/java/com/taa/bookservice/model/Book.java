package com.taa.bookservice.model;


import jakarta.persistence.*;

@Entity
@Table
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Float price;

    private Long userId;

    private String author;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
