# 📌 **API REST Spring Boot**
Este proyecto es una **API REST** desarrollada con **Spring Boot** y **Java 21**, utilizando **PostgreSQL** como base de datos y **Docker** para su gestión.
## Funcionalidad

- **Cálculo con porcentaje dinámico** (suma + porcentaje del servicio externo o caché).
- **Caché del porcentaje** (almacenado 30 min, usado si el servicio externo falla).
- **Historial de llamadas** (fecha, endpoint, parámetros, respuesta/error, con paginación y registro asíncrono).


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
La API cuenta con documentación generada automáticamente con **Swagger**, lo que permite probar los endpoints de manera interactiva.

🔗 **Swagger UI**: http://localhost:8080/swagger-ui/index.html

📌 Desde **Swagger** puedes enviar peticiones **GET, POST, PUT y DELETE** y ver la estructura de los **request** y **responses**.

### 🌐 Llamada a la API
También puedes probar la API directamente desde Postman o tu navegador con la siguiente URL de ejemplo:
```bash
http://localhost:8080/calculate?num1=100&num2=10
```
Esto enviará una solicitud **GET** al endpoint `/calculate` con los parámetros `num1=100` y `num2=10`.

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

