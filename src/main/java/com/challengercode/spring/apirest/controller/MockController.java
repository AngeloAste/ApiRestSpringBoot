package com.challengercode.spring.apirest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mock-percentage")
public class MockController {

    @GetMapping
    public double getMockPercentage() {
        return 10.0; // 📌 Simulación: siempre devuelve un 10%
    }
}