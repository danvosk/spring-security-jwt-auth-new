package com.example.demo.dto.request;


import com.example.demo.entity.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private Role role; 
}