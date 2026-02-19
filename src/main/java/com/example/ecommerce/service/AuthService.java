package com.example.ecommerce.service;

import com.example.ecommerce.dto.AuthRequestDto;
import com.example.ecommerce.dto.AuthResponseDto;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtil jwtUtil;

    public AuthResponseDto register(AuthRequestDto request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        
        String token = jwtUtil.generateToken(user);
        AuthResponseDto response = new AuthResponseDto();
        response.setToken(token);
        response.setUserId(user.getId());
        response.setEmail(user.getEmail());
        
        return response;
    }

    public AuthResponseDto login(AuthRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        String token = jwtUtil.generateToken(user);
        AuthResponseDto response = new AuthResponseDto();
        response.setToken(token);
        response.setUserId(user.getId());
        response.setEmail(user.getEmail());

        return response;
    }
}