package com.challengercode.spring.apirest.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HolaController {
    @GetMapping("/")
    public String hola() {
        return "Hola Mundo!";
    }
    
}
