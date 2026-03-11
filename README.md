# API Rest de la aplicación Voll Med
![Banner del Proyecto](./img/banner_vollmed.png)

Voll.med es una API REST robusta desarrollada en Java con Spring Boot, diseñada para gestionar una clínica médica de manera eficiente. El sistema permite el registro de médicos y pacientes, la programación de consultas y un sistema de seguridad avanzado.

 Funcionalidades Principales
‍ Gestión de Médicos
CRUD Completo: Registro, listado paginado, actualización y exclusión lógica.

Soft Delete: Los médicos no se borran de la base de datos, se marcan como "inactivos" para mantener la integridad referencial.

Especialidades: Soporte para Ortopedia, Cardiología, Ginecología y Dermatología.

 Gestión de Pacientes
Registro Detallado: Manejo de datos personales y direcciones mediante objetos incrustados (@Embedded).

Validaciones: Uso de Bean Validation para asegurar que el email, documento y teléfono cumplan con los formatos correctos.

 Reserva de Consultas
Agendamiento Inteligente: Sistema que permite elegir un médico o asignar uno aleatoriamente según la especialidad y disponibilidad en la fecha solicitada.

Reglas de Negocio: Validaciones para evitar choques de horarios, médicos inactivos o pacientes sin disponibilidad.

Cancelamiento: Gestión de cancelaciones con registro de motivos.

 Seguridad y Autenticación
Stateless Authentication: Implementación de seguridad con Spring Security y JWT (JSON Web Tokens).

Protección de Endpoints: Uso de @SecurityRequirement para asegurar que solo usuarios autenticados accedan a la gestión médica.

 Tecnologías Utilizadas
Java 17

Spring Boot 3

Spring Data JPA (Persistencia)

Spring Security (Autenticación y Autorización)

Flyway (Migraciones de Base de Datos)

PostgreSQL (Base de Datos Relacional)

Lombok (Productividad)

Hibernate Validator (Validaciones de DTOs)

SpringDoc / OpenAPI (Documentación de la API)

 Estructura Destacada
 Tratamiento de Errores
El proyecto cuenta con un GestorDeErrores centralizado que utiliza @RestControllerAdvice. Esto permite capturar excepciones como 404 Not Found, 400 Bad Request (validaciones de campos) y errores de autenticación, devolviendo respuestas JSON claras y estandarizadas.

 Consultas Avanzadas
Se implementaron queries personalizadas en JPQL dentro de los Repositorios para lógica compleja, como la selección aleatoria de médicos disponibles:

SQL
SELECT m FROM Medico m
WHERE m.activo = true
AND m.especialidad = :especialidad
AND m.id NOT IN (SELECT c.medico.id FROM Consulta c WHERE c.fecha = :fecha)
ORDER BY function('random')
LIMIT 1
 Requisitos e Instalación
Clonar el repositorio.

Configurar las credenciales de PostgreSQL en src/main/resources/application.properties.

Ejecutar la aplicación (Flyway creará las tablas automáticamente).

Acceder a la documentación en /swagger-ui.html.

Proyecto desarrollado como parte de la formación integral de los tres cursos de Spring Boot 3 en Alura Latam.

Desarrollado por Miguel Ángel de la Cruz Lázaro
