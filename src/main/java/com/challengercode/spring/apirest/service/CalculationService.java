package com.challengercode.spring.apirest.service;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
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
            
            System.out.println("Servicio externo devuelve: " + percentage);
            
            if (percentage != null) {
                updateCache(percentage);
                return percentage;
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error obteniendo porcentaje externo: " + e.getMessage());
        }
    
        Cache cache = cacheManager.getCache("percentageCache");
        if (cache != null) {
            Double cachedPercentage = cache.get("percentage", Double.class);
            System.out.println("🔄 Recuperado de caché: " + cachedPercentage);
            return cachedPercentage;
        }
    
        throw new RuntimeException("No se pudo obtener el porcentaje de cálculo");
    }

    @CachePut(value = "percentageCache")
public void updateCache(Double percentage) {
    Cache cache = cacheManager.getCache("percentageCache");
    if (cache != null) {
        cache.put("percentage", percentage); // ✅ Guardar en caché
    }
}
    // Nuevo método para realizar el cálculo
    public double calculate(double num1, double num2) {
        double base = num1 + num2;
        Double percentage = getDynamicPercentage();
        
        if (percentage == null) {
            throw new RuntimeException("No se pudo obtener el porcentaje de cálculo");
        }
    
        return base + (base * (percentage / 100));
    }
}
