package com.challengercode.spring.apirest.controller;

import com.challengercode.spring.apirest.service.CalculationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculate")
public class CalculationController {

    private final CalculationService calculationService;

    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @GetMapping
    public double calculate(@RequestParam double num1, @RequestParam double num2) {
        double sum = num1 + num2;
        double percentage = calculationService.getDynamicPercentage();
        System.out.println(" Porcentaje obtenido: " + percentage); // Verificar valor obtenido
        return sum + (sum * (percentage / 100)); // Aplica el porcentaje dinámico
    }
}

