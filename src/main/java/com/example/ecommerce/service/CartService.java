package com.example.ecommerce.service;

import com.example.ecommerce.dto.CartDto;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public List<CartDto> getCartItems(User user) {
        List<CartItem> items = cartRepository.findByUser(user);
        // Convert List<CartItem> to List<CartDto>
    }

    public void addToCart(CartDto cartDto) {
        // Add item to cart logic
    }
}