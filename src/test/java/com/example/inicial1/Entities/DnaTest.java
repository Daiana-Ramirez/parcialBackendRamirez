package com.example.inicial1.Entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.inicial1.entities.Dna;
import org.junit.jupiter.api.Test;

class DnaTest {

    @Test
    void testDnaEntity() {
        Dna dna = new Dna();
        dna.setDna("ATCG");
        dna.setMutant(true);

        assertEquals("ATCG", dna.getDna());
        assertEquals(true, dna.isMutant());
    }
}
