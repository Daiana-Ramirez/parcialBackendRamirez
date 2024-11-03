package com.example.inicial1.controller;

import com.example.inicial1.dtos.DnaRequest;
import com.example.inicial1.dtos.DnaResponse;
import com.example.inicial1.services.DnaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DnaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DnaService dnaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCheckMutantReturnsOkIfMutant() throws Exception {
        // Configuramos el ADN simulado como mutante
        String[] dnaSequence = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        DnaRequest request = new DnaRequest();
        request.setDna(dnaSequence);

        // Simulamos la respuesta del servicio
        when(dnaService.analyzeDna(dnaSequence)).thenReturn(true);

        // Realizamos una solicitud POST a /mutant y verificamos que devuelva 200 OK
        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isMutant", is(true)));
    }

    @Test
    void testCheckMutantReturnsForbiddenIfNotMutant() throws Exception {
        // Configuramos el ADN simulado como no mutante
        String[] dnaSequence = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};
        DnaRequest request = new DnaRequest();
        request.setDna(dnaSequence);

        // Simulamos la respuesta del servicio
        when(dnaService.analyzeDna(dnaSequence)).thenReturn(false);

        // Realizamos una solicitud POST a /mutant y verificamos que devuelva 403 Forbidden
        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.isMutant", is(false)));
    }

    @Test
    void testCheckMutantReturnsBadRequestIfInvalidDna() throws Exception {
        // ADN inválido con un carácter no permitido
        String[] invalidDnaSequence = {"ATGXA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        DnaRequest request = new DnaRequest();
        request.setDna(invalidDnaSequence);

        // Realizamos una solicitud POST a /mutant y verificamos que devuelva 400 Bad Request
        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCheckMutantReturnsBadRequestIfDnaIsEmpty() throws Exception {
        // Configuramos el ADN como un array vacío
        DnaRequest request = new DnaRequest();
        request.setDna(new String[]{});  // Array vacío

        // Realizamos una solicitud POST a /mutant y verificamos que devuelva 400 Bad Request
        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCheckMutantReturnsBadRequestIfDnaIsNull() throws Exception {
        // Configuramos el ADN como nulo
        DnaRequest request = new DnaRequest();
        request.setDna(null);  // ADN nulo

        // Realizamos una solicitud POST a /mutant y verificamos que devuelva 400 Bad Request
        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}

