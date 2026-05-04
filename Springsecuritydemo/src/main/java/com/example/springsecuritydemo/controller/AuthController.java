package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.dto.LoginRequest;
import com.example.springsecuritydemo.dto.RegisterRequest;
import com.example.springsecuritydemo.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegisterRequest request){
       return  authService.register(request);
    }

    @GetMapping("/login")
    public String login(@RequestBody @Valid LoginRequest request)
    {
        return authService.login(request);
    }
}
