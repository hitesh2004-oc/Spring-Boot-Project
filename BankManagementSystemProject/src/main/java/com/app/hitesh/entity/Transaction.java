package com.app.hitesh.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type;

    private Double amount;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}