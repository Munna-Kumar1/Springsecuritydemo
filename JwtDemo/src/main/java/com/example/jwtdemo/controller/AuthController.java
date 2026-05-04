package com.example.jwtdemo.controller;

import com.example.jwtdemo.dto.AuthRequest;
import com.example.jwtdemo.dto.AuthResponse;
import com.example.jwtdemo.dto.RegisterRequest;
import com.example.jwtdemo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request)
    {
        service.register(request);
        return "User registered Successfully";
    }

    public AuthResponse login(@RequestBody AuthRequest request)
    {
        String token = service.login(request);
        return new AuthResponse(token);
    }
}
