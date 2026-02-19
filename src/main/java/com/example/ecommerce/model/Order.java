package com.example.ecommerce.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String shippingAddress;
    private BigDecimal totalAmount;
    private LocalDateTime orderDate;

    // Getters and Setters
}