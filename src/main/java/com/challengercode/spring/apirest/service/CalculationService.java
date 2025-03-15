package com.challengercode.spring.apirest.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CalculationService {

    private final RestTemplate restTemplate;

    public CalculationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double getDynamicPercentage() {
        // Simulación de servicio externo con un valor fijo (ejemplo: 10%)
        String url = "http://localhost:8080/mock-percentage";
        return restTemplate.getForObject(url, Double.class);
    }
}
