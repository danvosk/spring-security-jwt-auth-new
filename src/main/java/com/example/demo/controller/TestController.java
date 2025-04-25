package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, secured world!";
    }
    
    @GetMapping("/read")
    public String read() {
        return "✅ GET başarılı (Tüm roller)";
    }

    @PostMapping("/create")
    public String create() {
        return "✅ POST başarılı (TRAINER & ADMIN)";
    }

    @PutMapping("/update")
    public String update() {
        return "✅ PUT başarılı (Yalnızca ADMIN)";
    }

    @DeleteMapping("/delete")
    public String delete() {
        return "✅ DELETE başarılı (Yalnızca ADMIN)";
    }
}
