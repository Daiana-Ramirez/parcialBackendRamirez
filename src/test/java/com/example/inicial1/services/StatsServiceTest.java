package com.example.inicial1.services;

import com.example.inicial1.dtos.StatsResponse;
import com.example.inicial1.repositories.DnaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class StatsServiceTest {

    @Mock
    private DnaRepository dnaRepository;

    @InjectMocks
    private StatsService statsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetStatsWithMutantsAndHumans() {
        // Configuramos los valores simulados para el repositorio
        when(dnaRepository.countByIsMutant(true)).thenReturn(40L);  // Número de ADN mutante
        when(dnaRepository.countByIsMutant(false)).thenReturn(100L); // Número de ADN humano

        // Ejecutamos el método que queremos probar
        StatsResponse statsResponse = statsService.getStats();

        // Verificamos los resultados
        assertEquals(40L, statsResponse.getCountMutantDna());
        assertEquals(100L, statsResponse.getCountHumanDna());
        assertEquals(0.4, statsResponse.getRatio());
    }

    @Test
    void testGetStatsWithNoHumans() {
        // Configuramos el caso donde no hay ADN humano
        when(dnaRepository.countByIsMutant(true)).thenReturn(40L);  // Número de ADN mutante
        when(dnaRepository.countByIsMutant(false)).thenReturn(0L);  // Número de ADN humano

        // Ejecutamos el método que queremos probar
        StatsResponse statsResponse = statsService.getStats();

        // Verificamos los resultados, esperando un ratio de 0.0 debido a la ausencia de ADN humano
        assertEquals(40L, statsResponse.getCountMutantDna());
        assertEquals(0L, statsResponse.getCountHumanDna());
        assertEquals(0.0, statsResponse.getRatio());
    }

    @Test
    void testGetStatsWithNoData() {
        // Configuramos el caso donde no hay datos de ADN
        when(dnaRepository.countByIsMutant(true)).thenReturn(0L);  // Número de ADN mutante
        when(dnaRepository.countByIsMutant(false)).thenReturn(0L); // Número de ADN humano

        // Ejecutamos el método que queremos probar
        StatsResponse statsResponse = statsService.getStats();

        // Verificamos los resultados, esperando 0 para todo
        assertEquals(0L, statsResponse.getCountMutantDna());
        assertEquals(0L, statsResponse.getCountHumanDna());
        assertEquals(0.0, statsResponse.getRatio());
    }
}
