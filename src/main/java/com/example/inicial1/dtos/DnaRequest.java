package com.example.inicial1.dtos;


import com.example.inicial1.validators.ValidDna;
import jakarta.validation.Valid;
import lombok.*;


@Setter
@Getter
public class DnaRequest { //es el objeto que contiene el ADN en forma de un array de String[] (cada cadena representa una fila del ADN).
    @ValidDna //se usa para validar el array de ADN, verificando que contenga únicamente secuencias válidas de letras que representen las bases nitrogenadas del ADN (A, T, C, G), que las cadenas tengan la longitud adecuada, o cualquier otra regla de validación.
    // es decir,  esto le dice al validador de Java que debe aplicar la validación definida en DnaValidator sobre el valor de dna.
    private String[] dna;
}
