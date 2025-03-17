package com.challengercode.spring.apirest.controller;

import com.challengercode.spring.apirest.service.CalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CalculationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CalculationService calculationService;

    @InjectMocks
    private CalculationController calculationController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(calculationController).build();
    }

    @Test
    void testCalculateEndpoint() throws Exception {
        // DEBES mockear el método usado internamente (getDynamicPercentage)
        when(calculationService.getDynamicPercentage()).thenReturn(10.0);
        // NO mockees calculate, deja que ejecute la lógica real usando el mock anterior.

        mockMvc.perform(get("/calculate")
                .param("num1", "100")
                .param("num2", "50")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("165.0")); // 100 + 50 = 150 + 10% = 165.0
    }
}
