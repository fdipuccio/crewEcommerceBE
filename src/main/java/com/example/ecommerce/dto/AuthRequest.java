package com.example.ecommerce.dto;

import javax.validation.constraints.NotBlank;

public class AuthRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    // Getters and Setters
}