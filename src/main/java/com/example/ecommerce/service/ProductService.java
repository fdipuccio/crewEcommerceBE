package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()  
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    public ProductDto getProductById(Long id) {
        return convertToDto(productRepository.findById(id).orElse(null));
    }

    private ProductDto convertToDto(Product product) {
        // Convert Product to ProductDto
    }
}