# 📌 API REST Spring Boot
Este proyecto es una API REST desarrollada con Spring Boot y Java 21, utilizando PostgreSQL como base de datos y Docker para su gestión.

🚀 Requisitos
Antes de ejecutar el proyecto, asegúrate de tener instalado:
JDK 21
Apache Maven
Docker y Docker Compose

🔧 Configuración y Ejecución

1️⃣ Levantar PostgreSQL con Docker
Ejecuta el siguiente comando en la raíz del proyecto para iniciar la base de datos:
docker-compose up -d

2️⃣ Ejecutar la API
Inicia la API con Maven:
mvn spring-boot:run

3️⃣ Acceder a Swagger UI
Una vez que la API esté corriendo, la documentación estará disponible en:
🔗 Swagger UI

✅ Pruebas
Para ejecutar los tests, usa el siguiente comando:
mvn test
Las pruebas se encuentran en src/test/java/com/challengercode/spring/apirest/

✅ Comandos útiles para ejecutar las pruebas

Si quieres ejecutar todas las pruebas, usa:
mvn clean test
Si solo quieres ejecutar las pruebas de los controladores:
mvn -Dtest=com.challengercode.spring.apirest.controller.CalculationControllerTest test
Si solo quieres ejecutar las pruebas de los servicios:
mvn -Dtest=com.challengercode.spring.apirest.service.CalculationServiceUnitTest test
