package com.challengercode.spring.apirest.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CalculationService {

    private final RestTemplate restTemplate;
    private final CacheManager cacheManager;

    public CalculationService(RestTemplate restTemplate, CacheManager cacheManager) {
        this.restTemplate = restTemplate;
        this.cacheManager = cacheManager;
    }

    @Cacheable(value = "percentageCache", unless = "#result == null")
    public Double getDynamicPercentage() {
        try {
            String url = "http://localhost:8080/mock-percentage"; // Simula un servicio externo
            Double percentage = restTemplate.getForObject(url, Double.class);
            if (percentage != null) {
                updateCache(percentage); // Almacenar en caché
            }
            return percentage;
        } catch (Exception e) {
            // Si el servicio externo falla, intentamos recuperar el último valor en caché
            Double cachedPercentage = (Double) cacheManager.getCache("percentageCache")
                    .get("percentageCache", Double.class);

            if (cachedPercentage != null) {
                return cachedPercentage; // Retorna el último valor almacenado en caché
            }

            throw new RuntimeException("No se pudo obtener el porcentaje y no hay valores en caché");
        }
    }

    @CachePut(value = "percentageCache")
    public void updateCache(Double percentage) {
        // Actualiza la caché con el nuevo valor del porcentaje
    }

    // Nuevo método para realizar el cálculo
    public double calculate(double num1, double num2) {
        Double percentage = getDynamicPercentage(); // Obtiene el porcentaje dinámico
        if (percentage == null) {
            throw new RuntimeException("No se pudo obtener el porcentaje dinámico");
        }
        return (num1 + num2) * (1 + (percentage / 100));
    }
}
