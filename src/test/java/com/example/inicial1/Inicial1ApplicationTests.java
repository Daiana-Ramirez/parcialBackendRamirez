package com.example.inicial1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Inicial1ApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	void mainTest() {
		// Llama al método main para verificar que se puede ejecutar sin problemas
		Inicial1Application.main(new String[] {});
	}

}
