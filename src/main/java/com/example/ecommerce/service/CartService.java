package com.example.ecommerce.service;

import com.example.ecommerce.dto.CartDto;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public void addToCart(Long userId, Long productId, Integer quantity) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        CartItem item = new CartItem();
        item.setUser(new User(userId)); // Assuming User is correctly instantiated
        item.setProduct(product);
        item.setQuantity(quantity);
        cartRepository.save(item);
    }

    public CartDto getCartItems(Long userId) {
        List<CartItem> items = cartRepository.findByUserId(userId);
        CartDto cartDto = new CartDto();
        cartDto.setItems(items.stream().map(this::convertToDto).collect(Collectors.toList()));
        return cartDto;
    }

    private CartItemDto convertToDto(CartItem item) {
        CartItemDto dto = new CartItemDto();
        dto.setId(item.getId());
        dto.setProductId(item.getProduct().getId());
        dto.setQuantity(item.getQuantity());
        return dto;
    }
}