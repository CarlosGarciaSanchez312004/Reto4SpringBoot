# Reto4SpringBoot - API de Gestión de Museos

Este proyecto es una API REST desarrollada con **Spring Boot** y **MongoDB** para la gestión de información sobre museos. Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre museos, así como consultas específicas por ubicación y estadísticas.

La aplicación incluye seguridad básica mediante **Spring Security**, requiriendo autenticación para las operaciones de modificación de datos.

## Tecnologías Utilizadas

*   **Java 17**
*   **Spring Boot 3.x** (Web, Data MongoDB, Security)
*   **MongoDB** (Base de datos NoSQL)
*   **Lombok** (Para reducir el código repetitivo)
*   **Maven** (Gestión de dependencias)

## Configuración y Ejecución

1.  **Requisitos previos:**
    *   Tener instalado Java 17 o superior.
    *   Tener una instancia de MongoDB en ejecución (local o remota).
    *   Maven instalado (o usar el wrapper `mvnw` incluido).

2.  **Configuración de la Base de Datos:**
    Asegúrate de configurar la conexión a MongoDB en el archivo `application.properties` (o `application.yml`). Por defecto, Spring Boot intentará conectarse a `mongodb://localhost:27017/test` si no se especifica otra cosa.

3.  **Ejecución:**
    ```bash
    ./mvnw spring-boot:run
    ```

## Seguridad

La API está protegida con **Autenticación Básica (HTTP Basic Auth)**.

*   **Acceso Público (Sin autenticación):**
    *   Todos los endpoints `GET` (Consultas de información).
*   **Acceso Restringido (Requiere autenticación):**
    *   Endpoints `POST`, `PUT`, `DELETE` (Crear, Actualizar, Eliminar).
    *   Se requiere un usuario registrado en la base de datos (colección `users`).

## Endpoints de la API

La URL base para los endpoints de museos es `/api/museos`.

### 1. Obtener todos los museos

*   **Método:** `GET`
*   **URL:** `/api/museos`
*   **Descripción:** Recupera la lista completa de museos registrados.
*   **Respuesta Exitosa (200 OK):**
    ```json
    [
      {
        "_id": "65df...",
        "referenciaId": 2714,
        "nombre": "Museo del Prado",
        "municipality": "Madrid",
        "province": "Madrid",
        "address": "Calle Ruiz de Alarcón 23",
        "phone": "913302800",
        "email": "museo.nacional@prado.es",
        "web": "https://www.museodelprado.es"
      }
    ]
    ```

### 2. Obtener un museo por ID

*   **Método:** `GET`
*   **URL:** `/api/museos/{id}`
*   **Descripción:** Busca un museo por su ID interno de MongoDB.
*   **Parámetros:** `id` (String) - ID del museo.
*   **Respuesta Exitosa (200 OK):** Objeto JSON del museo.
*   **Error (404 Not Found):**
    ```json
    {
      "message": "No existe el museo con ID: {id}",
      "error": "Recurso no encontrado",
      "status": 404
    }
    ```

### 3. Crear un nuevo museo (Requiere Autenticación)

*   **Método:** `POST`
*   **URL:** `/api/museos`
*   **Cuerpo (JSON):**
    ```json
    {
      "referenciaId": 9999,
      "nombre": "Nuevo Museo de Arte",
      "municipality": "Barcelona",
      "province": "Barcelona",
      "address": "Av. Diagonal 123",
      "phone": "934567890",
      "email": "contacto@nuevomuseo.com",
      "web": "http://www.nuevomuseo.com"
    }
    ```
*   **Respuesta Exitosa (201 Created):** Devuelve el objeto creado con su nuevo ID.

### 4. Actualizar un museo (Requiere Autenticación)

*   **Método:** `PUT`
*   **URL:** `/api/museos/{id}`
*   **Descripción:** Actualiza los datos de un museo existente.
*   **Parámetros:** `id` (String) - ID del museo a actualizar.
*   **Cuerpo (JSON):** Datos a actualizar (similar al POST).
*   **Respuesta Exitosa (200 OK):** Devuelve el objeto actualizado.
*   **Error (404 Not Found):** Si el ID no existe.

### 5. Eliminar un museo (Requiere Autenticación)

*   **Método:** `DELETE`
*   **URL:** `/api/museos/{id}`
*   **Descripción:** Elimina un museo del sistema.
*   **Parámetros:** `id` (String) - ID del museo a eliminar.
*   **Respuesta Exitosa (204 No Content):** Sin contenido.
*   **Error (404 Not Found):** Si el ID no existe.

### 6. Buscar museos por provincia

*   **Método:** `GET`
*   **URL:** `/api/museos/provincia/{provincia}`
*   **Parámetros:** `provincia` (String) - Nombre de la provincia.
*   **Respuesta Exitosa (200 OK):** Lista de museos en esa provincia.

### 7. Buscar museo por nombre

*   **Método:** `GET`
*   **URL:** `/api/museos/nombre/{nombre}`
*   **Parámetros:** `nombre` (String) - Nombre exacto del museo.
*   **Respuesta Exitosa (200 OK):** Objeto del museo encontrado.
*   **Error (404 Not Found):** Si no se encuentra.

### 8. Listar todas las provincias

*   **Método:** `GET`
*   **URL:** `/api/museos/provincias/lista`
*   **Descripción:** Devuelve una lista de nombres de todas las provincias que tienen museos registrados.
*   **Respuesta Exitosa (200 OK):**
    ```json
    [
      "Alicante",
      "Barcelona",
      "Madrid",
      "Valencia"
    ]
    ```

### 9. Estadísticas por provincia

*   **Método:** `GET`
*   **URL:** `/api/museos/stats/provincias`
*   **Descripción:** Devuelve un mapa con el conteo de museos por cada provincia.
*   **Respuesta Exitosa (200 OK):**
    ```json
    {
      "Madrid": 45,
      "Barcelona": 38,
      "Valencia": 20
    }
    ```

## Manejo de Errores

La API utiliza un formato estándar para las respuestas de error:

```json
{
  "message": "Descripción detallada del error",
  "error": "Tipo de error",
  "status": 400
}
```

## Estructura del Proyecto

*   `controllers`: Controladores REST (`MuseoController`).
*   `services`: Lógica de negocio (`MuseoService`).
*   `repositories`: Interfaces de acceso a datos (`MuseoRepository`, `UserRepository`).
*   `entities`: Modelos de datos (`Museo`, `UserDB`).
*   `security`: Configuración de seguridad (`SecurityConfig`, `AppUserDetailsService`).
*   `exceptions`: Manejo global de excepciones (`GlobalExceptionHandler`, `MuseoNotFoundException`).
*   `dto`: Objetos de transferencia de datos (`ErrorResponseDTO`).
