package com.challengercode.spring.apirest.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.client.RestTemplate;

/**
 * Pruebas unitarias para CalculationService
 * Enfoque: Mockeamos todas las dependencias externas para probar la lógica de negocio de manera aislada.
 */
@ExtendWith(MockitoExtension.class) // Habilita Mockito para simulación de dependencias
class CalculationServiceUnitTest {

    // Simula el servicio externo
    @Mock
    private RestTemplate restTemplate;

    // Simula el administrador de caché
    @Mock
    private CacheManager cacheManager;

    // Simula el acceso a la caché
    @Mock
    private Cache cache;

    // Instancia de la clase bajo prueba con mocks
    @InjectMocks
    private CalculationService calculationService;

    private final String EXTERNAL_URL = "http://localhost:8080/mock-percentage";

    @BeforeEach
    void setup() {
        lenient().when(cacheManager.getCache("percentageCache")).thenReturn(cache);
    }

    @Test
    void testCalculateWithCachedPercentage() {
        // Simula un valor de porcentaje en caché
        when(cache.get("percentage", Double.class)).thenReturn(10.0);
        when(calculationService.getDynamicPercentage()).thenReturn(10.0);

        double result = calculationService.calculate(100, 50);

        // Verifica que el resultado sea correcto
        assertEquals(165.0, result); // 100 + 50 = 150 + 10% = 165
        verify(cache, times(1)).get("percentage", Double.class);
    }

    @Test
    void testCalculateWithExternalPercentage() {
        lenient().when(cache.get("percentage", Double.class)).thenReturn(null);
        lenient().when(restTemplate.getForObject(EXTERNAL_URL, Double.class)).thenReturn(20.0);
    
        // 🔥 Importante: Evita el error de stub innecesario en `put(...)`
        doNothing().when(cache).put("percentage", 20.0);
    
        calculationService.calculate(200, 100);
    
        verify(cache, times(1)).put("percentage", 20.0); // Verifica que realmente se llama a `put`
    }

    @Test
    void testCalculateWithNoPercentageAvailable() {
        // Simula que no hay caché ni respuesta del servicio externo
        when(cache.get("percentage", Double.class)).thenReturn(null);
        when(restTemplate.getForObject(EXTERNAL_URL, Double.class)).thenReturn(null);
        when(calculationService.getDynamicPercentage()).thenThrow(new RuntimeException("No se pudo obtener el porcentaje de cálculo"));

        // Verifica que se lance la excepción
        RuntimeException exception = assertThrows(RuntimeException.class, () -> calculationService.calculate(50, 50));
        assertEquals("No se pudo obtener el porcentaje de cálculo", exception.getMessage());
    }
}
