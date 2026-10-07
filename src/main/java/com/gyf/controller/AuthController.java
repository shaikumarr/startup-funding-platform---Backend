package com.gyf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gyf.dto.LoginRequest;
import com.gyf.dto.LoginResponse;
import com.gyf.dto.LoginUserResponse;
import com.gyf.entity.User;
import com.gyf.repository.UserRepository;
import com.gyf.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {

        if (request == null ||
                request.getEmail() == null ||
                request.getPassword() == null) {

            throw new RuntimeException(
                    "Email and password are required");
        }

        User user =
                userRepository
                        .findFirstByEmailIgnoreCase(
                                request.getEmail().trim())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User Not Found"));

        if (!user.getPassword()
                .equals(request.getPassword())) {

            throw new RuntimeException(
                    "Invalid Password");
        }

        String token =
                JwtUtil.generateToken(
                        user.getEmail());

        LoginUserResponse userResponse =
                new LoginUserResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole(),
                        user.getStatus());

        return new LoginResponse(
                token,
                userResponse);
    }
}