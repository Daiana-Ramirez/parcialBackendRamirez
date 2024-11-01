package com.example.inicial1.controllers;

import com.example.inicial1.dtos.StatsResponse;
import com.example.inicial1.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatsController {

    //@Autowired :La inyección de dependencias con @Autowired en un campo no puede ser usada si ese campo es final, ya que Spring necesita poder asignarle el valor de la dependencia, y el campo final hace que esa asignación solo pueda ocurrir una vez.
    private final StatsService statsService;

    public StatsController(StatsService statsService){
        this.statsService = statsService;
    }

    @GetMapping // cuando alguien haga una solicitud GET a /stats, este método será ejecutado.
    public StatsResponse getStats(){ //método que devuelve un objeto del tipo StatsResponse
        return statsService.getStats(); //La respuesta de este método se envía directamente al cliente que realizó la solicitud.
    }

    //Si se necesitara más control sobre la respuesta (como devolver un estado HTTP diferente o agregar encabezados), se podría modificar el método getStats() para que use ResponseEntity:
    /*
    public ResponseEntity<StatsResponse> getStats() {
    StatsResponse statsResponse = statsService.getStats();
    return ResponseEntity.status(HttpStatus.OK).body(statsResponse);
}

     */
}
