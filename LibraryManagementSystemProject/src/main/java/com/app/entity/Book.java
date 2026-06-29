package com.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

public class Book {

    @Id

    @GeneratedValue(
            strategy = GenerationType.IDENTITY)

    private Integer id;

    private String title;

    private String author;

    private Double price;

    private String status;
}