package com.example.ecommerce.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private Double total;
    private LocalDateTime orderDate = LocalDateTime.now();

    // Getters and Setters
}