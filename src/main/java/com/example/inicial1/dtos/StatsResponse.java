package com.example.inicial1.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class StatsResponse {
    @JsonProperty("count_mutant_dna")
    private long countMutantDna;  //El número de secuencias de ADN que han sido identificadas como mutantes.

    @JsonProperty("count_human_dna")
    private long countHumanDna; //El número de secuencias de ADN que han sido identificadas como humanas (no mutantes).

    private double ratio; //La proporción entre las secuencias de ADN mutantes y humanas.
// @JsonProperty se utiliza para mapear los nombres de los campos en JSON con sus respectivos campos en la clase. Esto es útil cuando se desea un formato específico en la salida JSON.
}
