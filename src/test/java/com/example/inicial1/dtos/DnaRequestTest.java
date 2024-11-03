package com.example.inicial1.dtos;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DnaRequestTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidDnaSequence() {
        DnaRequest dnaRequest = new DnaRequest();
        dnaRequest.setDna(new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"});
        Set<ConstraintViolation<DnaRequest>> violations = validator.validate(dnaRequest);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidDnaSequence() {
        DnaRequest dnaRequest = new DnaRequest();
        dnaRequest.setDna(new String[]{"ATGCGA", "CAGTGC", "TTXTGT", "AGAAGG", "CCCCTA", "TCACTG"});
        Set<ConstraintViolation<DnaRequest>> violations = validator.validate(dnaRequest);
        assertFalse(violations.isEmpty());
    }
}

