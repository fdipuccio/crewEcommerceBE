package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CartDto;
import com.example.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public List<CartDto> getCartItems(Principal principal) {
        return cartService.getCartItems(principal.getName());
    }

    @PostMapping
    public void addToCart(@Valid @RequestBody CartDto cartDto) {
        cartService.addToCart(cartDto);
    }
}