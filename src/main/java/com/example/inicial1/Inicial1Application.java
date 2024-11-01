package com.example.inicial1;


import com.example.inicial1.entities.Dna;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Inicial1Application {

	public static void main(String[] args) {
		SpringApplication.run(Inicial1Application.class, args);
		System.out.println("Aplicación Spring Boot funcionando");

		// Mover la lógica a un método
		iniciarProceso();
	}

	// Método para inicializar la lógica del ADN
	public static void iniciarProceso() {
		// Cadena de ADN de ejemplo (convertimos la lista en un String)
		String dna_example = "ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG";

		// Crear objeto Dna usando el patrón Builder (si es que está implementado correctamente)
		Dna persona1 = Dna.builder().dna(dna_example).build();

		// Lógica para manejar el resultado (asumiendo que getDna() retorna un String)
		boolean result = esMutante(persona1.getDna());
		System.out.println("¿Es mutante?: " + result);
	}

	// Método de ejemplo para verificar si es mutante
	public static boolean esMutante(String dna) {
		// Lógica de verificación de mutantes va aquí
		return dna != null && !dna.isEmpty();  // Ejemplo básico de validación
	}
}
