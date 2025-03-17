# 📌 **API REST Spring Boot**
Este proyecto es una **API REST** desarrollada con **Spring Boot** y **Java 21**, utilizando **PostgreSQL** como base de datos y **Docker** para su gestión.

## 🚀 **Requisitos**
Antes de ejecutar el proyecto, asegúrate de tener instalado:

- **JDK 21**
- **Apache Maven**
- **Docker** y **Docker Compose**

## 🔧 **Configuración y Ejecución**

### 1️⃣ **Levantar PostgreSQL con Docker**
Ejecuta el siguiente comando en la raíz del proyecto para iniciar la base de datos:
```bash
docker-compose up -d
```

### 2️⃣ **Ejecutar la API**
Inicia la API con Maven:
```bash
mvn spring-boot:run
```

### 3️⃣ **Acceder a Swagger UI**
Una vez que la API esté corriendo, la documentación estará disponible en:

🔗 **Swagger UI**: http://localhost:8080/swagger-ui/index.html

## 🧪 **Pruebas**
Para ejecutar los tests, usa el siguiente comando:
```bash
mvn test
```
Las pruebas se encuentran en:
```plaintext
src/test/java/com/challengercode/spring/apirest/
```

## 🛠️ **Comandos útiles para ejecutar las pruebas**

- Ejecutar todas las pruebas:
```bash
mvn clean test
```

- Solo pruebas de controladores:
```bash
mvn -Dtest=com.challengercode.spring.apirest.controller.CalculationControllerTest test
```

- Solo pruebas de servicios:
```bash
mvn -Dtest=com.challengercode.spring.apirest.service.CalculationServiceUnitTest test
```

