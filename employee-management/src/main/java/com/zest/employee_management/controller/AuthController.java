package com.zest.employee_management.controller;

import com.zest.employee_management.dto.LoginRequest;
import com.zest.employee_management.dto.RegisterRequest;
import com.zest.employee_management.entity.User;
import com.zest.employee_management.repository.UserRepository;
import com.zest.employee_management.security.JwtUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request
    ) {

        User user = new User();

        user.setUsername(request.getUsername());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        userRepository.save(user);

        return "User Registered Successfully";
    }

    @PostMapping("/login")
    public String login(
            @RequestBody LoginRequest request
    ) {

        User user = userRepository
                .findByUsername(
                        request.getUsername()
                )
                .orElseThrow(
                        () -> new RuntimeException(
                                "User Not Found"
                        )
                );

        boolean passwordMatches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if (!passwordMatches) {

            throw new RuntimeException(
                    "Invalid Password"
            );
        }

        return jwtUtil.generateToken(
                user.getUsername()
        );
    }
}