package com.example.inicial1.controller;

import com.example.inicial1.dtos.StatsResponse;
import com.example.inicial1.services.StatsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StatsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatsService statsService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetStatsReturnsCorrectResponse() throws Exception {
        // Configura el mock para devolver un objeto StatsResponse simulado
        StatsResponse response = new StatsResponse(40, 100, 0.4);
        when(statsService.getStats()).thenReturn(response);

        // Realiza una solicitud GET a /stats y verifica los valores en la respuesta JSON
        mockMvc.perform(get("/stats")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count_mutant_dna", is(40)))
                .andExpect(jsonPath("$.count_human_dna", is(100)))
                .andExpect(jsonPath("$.ratio", is(0.4)));
    }

    @Test
    void testGetStatsWithZeroHumanDna() throws Exception {
        // Configura el mock para un caso donde countHumanDna es 0 para evitar división por cero
        StatsResponse response = new StatsResponse(40, 0, 0.0);
        when(statsService.getStats()).thenReturn(response);

        // Realiza la solicitud y verifica que el valor de ratio sea 0.0 y no se divida por cero
        mockMvc.perform(get("/stats")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count_mutant_dna", is(40)))
                .andExpect(jsonPath("$.count_human_dna", is(0)))
                .andExpect(jsonPath("$.ratio", is(0.0)));
    }

    @Test
    void testGetStatsWithMockMvcIntegration() throws Exception {
        // Configura un caso general donde el servicio devuelve valores normales
        StatsResponse response = new StatsResponse(0, 0, 0.0);
        when(statsService.getStats()).thenReturn(response);

        // Verifica que los valores en el JSON sean todos 0 como se configuró
        mockMvc.perform(get("/stats")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count_mutant_dna", is(0)))
                .andExpect(jsonPath("$.count_human_dna", is(0)))
                .andExpect(jsonPath("$.ratio", is(0.0)));
    }
}


