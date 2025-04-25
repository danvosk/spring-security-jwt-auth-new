package com.example.demo.controller;

import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.response.AuthResponse;
import org.springframework.http.HttpStatus;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest req) {

        System.out.println("Gelen rol: " + req.getRole());
    
   /* if (req.getRole() == Role.ADMIN) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new AuthResponse("Admin olarak kayıt olunamaz"));
    } */

    var u = User.builder()
            .username(req.getUsername())
            .password(req.getPassword())
            .role(req.getRole())
            .build();

    var saved = userService.register(u);
    var token = jwtUtil.generateToken(saved.getUsername());

    return ResponseEntity.ok(new AuthResponse(token));
}

}