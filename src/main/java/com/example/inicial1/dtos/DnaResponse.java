package com.example.inicial1.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor //constructor que acepta un parametro booleano (ismutant)
@Setter
@Getter
public class DnaResponse {
    @JsonProperty("isMutant")  // Forzar que el nombre en JSON sea "isMutant"
    private boolean isMutant;

    public boolean isMutant() {
        return isMutant;
    }
}
