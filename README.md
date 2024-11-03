Proyecto Mutante 🧬
Este proyecto tiene como objetivo detectar si un humano es mutante basándose en su secuencia de ADN. El programa recibe como parámetro un array de Strings que representan cada fila de una tabla de NxN con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A, T, C, G), que representan cada base nitrogenada del ADN.

Un humano es considerado mutante si se encuentran más de una secuencia de cuatro letras iguales, de forma oblicua, horizontal o vertical.

📋 Funcionamiento
El ADN es recibido como un arreglo de Strings, donde cada String representa una fila de una tabla de NxN.
Las letras permitidas en cada fila son: A, T, C, G, que representan las bases nitrogenadas del ADN.
Se determina que una persona es mutante si se encuentra más de una secuencia de cuatro letras consecutivas en cualquier dirección (oblicua, horizontal o vertical).



🌐 Ejecución
1- Clonar el proyecto: https://github.com/Daiana-Ramirez/parcialBackendRamirez.git

2-Abrir el proyecto en IntelliJ IDEA:

Abre IntelliJ IDEA.

Selecciona File > Open y navega hasta la carpeta del proyecto clonado.

Haz clic en OK para abrir el proyecto. 3- Instalar las dependencias necesarias.

4- Ejecutar el proyecto:

Haz clic en el botón de Run (verde) en IntelliJ para iniciar la aplicación.
5- Abrir postman y enviar peticiones con sus correspondientes endpoint según la funcionalidad que se desee usar.

• Método para detectar mutante
✓ Endpoint: /mutant

✓ Método: POST

✓ Recibe un JSON con la matriz de ADN y determina si es mutante.

Ejemplo:

```json
{
    "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCATCG"]
}

•Posibles respuestas:

➣Retornar HTTP 200 OK si se detecta un mutante.
➣Retornar HTTP 403 Forbiddensi no es mutante.
➣Retornar HTTP 400 BAD_REQUEST en caso de que se produzca una excepción por introducir un ADN no válido o por alguna razón extra.

✓ Endpoint: /stats

✓ Método: GET

✓ Devuelve un JSON con el conteo de mutantes y humanos verificados.

Dónde:
➣count_mutant_dna: indica número total de secuencias de ADN que se han indentificado como mutantes en la base de datos.

➣count_human_dna: indica número de secuencias de ADN que se han identificado como humanas en la base de datos.

➣ratio: indica la proporción de mutantes entre el total de secuencias de ADN de mutantes y humanas verificadas en la base de datos.


🔗 Enlace para Pruebas y Documentación
➣Render:La aplicación ha sido desplegada en Render y está disponible en: https://parcialbackendramirez.onrender.com/. Colocar al lado de el link /mutant o /stats .

➣Swagger UI: Si la API está en funcionamiento, puedes realizar pruebas en http://localhost:8080/swagger-ui/index.html.

➣Postman: También puedes utilizar Postman para realizar pruebas.

➣Render:La aplicación ha sido desplegada en Render y está disponible en: https://parcialbackendramirez.onrender.com/. Colocar al lado de el link /mutant o /stats .

➣Carpeta Nivel 2 y 3: se encuentra información sobre:

➜El Diagrama de Secuencia aplicada para el algoritmo de detectar mutantes y el conteo de ADN humanos y mutantes.

➜TestCoverage  que indica cuánto del código de éste proyecto está siendo cubierto o ejecutado por las pruebas automáticas.

➜Pruebas unitarias aplicadas para testear el proyecto.

➜Las pruebas de Stress aplicadas en Jmeter para éste proyecto.
