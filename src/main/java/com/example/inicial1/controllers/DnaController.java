package com.example.inicial1.controllers;

import com.example.inicial1.dtos.DnaRequest;
import com.example.inicial1.dtos.DnaResponse;
import com.example.inicial1.services.DnaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mutant")
public class DnaController {

    private final DnaService dnaService;

    // Constructor para inyectar el servicio
    public DnaController(DnaService dnaService) {
        this.dnaService = dnaService;
    }  //El propósito de esta línea es permitir que el controlador utilice el servicio DnaService para realizar operaciones relacionadas con el análisis de ADN.


    //El método checkMutant recibe una secuencia de ADN en el cuerpo de una solicitud POST también Verifica si la secuencia de ADN corresponde a un mutante usando el servicio dnaService.analyzeDna().
    @PostMapping
    public ResponseEntity<DnaResponse> validarDna(@Valid @RequestBody DnaRequest dnaRequest) {
        boolean isMutant = dnaService.analyzeDna(dnaRequest.getDna());  //Obtiene la secuencia de ADN enviada por el usuario en el cuerpo de la solicitud HTTP y guarda el valor devuelto por el método analyzeDna de dnaService
        DnaResponse dnaResponse = new DnaResponse(isMutant);  //Este objeto encapsula la respuesta que se devolverá al cliente que hizo la solicitud

        if (isMutant) {
            // Devuelve la respuesta (HTTP 200 OK si es un mutante) con el objeto DnaResponse
            return ResponseEntity.status(HttpStatus.OK).body(dnaResponse);
        } else {
            // Devuelve la respuesta(HTTP 403 Forbidden si no lo es) con el objeto DnaResponse
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(dnaResponse);
        }
    }

    @GetMapping
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("El endpoint /mutant está funcionando");
    }
}
