package com.example.jwtdemo.service;

import com.example.jwtdemo.dto.AuthRequest;
import com.example.jwtdemo.dto.RegisterRequest;
import com.example.jwtdemo.entity.User;
import com.example.jwtdemo.repo.UserRepository;
import com.example.jwtdemo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    public void register(RegisterRequest request) {

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        repo.save(user);
    }

    public String login(AuthRequest request) {
        User user =repo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!encoder.matches(request.getPassword(), user.getPassword())){
            throw  new RuntimeException("Invalid Password");
        }

        return  jwtUtil.generateToken(user.getUsername());
    }
}
