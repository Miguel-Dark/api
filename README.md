# API Rest de la aplicación Voll Med
![Banner del Proyecto](./img/banner_vollmed.png)

<p align="left">
  <img src="https://img.shields.io/badge/Java_17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=black" />
  <img src="https://img.shields.io/badge/Spring_Boot_3.3.10-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=black" />
  <img src="https://img.shields.io/badge/PostgreSQL_17-316192?style=for-the-badge&logo=postgresql&logoColor=black" />
  <img src="https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring-security&logoColor=black" />
</p>

<p><strong>Voll.med</strong> es una <strong>API REST</strong> robusta desarrollada en <strong>Java</strong> con <strong>Spring Boot</strong>, diseñada para gestionar una clínica médica de manera eficiente. El sistema permite el registro de médicos y pacientes, la programación de consultas y un sistema de seguridad avanzado.</p>

<h2>Funcionalidades Principales</h2>

<h3>Gestión de Médicos</h3>
<ul>
<li><strong>CRUD Completo:</strong> Registro, listado paginado, actualización y exclusión lógica.</li>
<li><strong>Soft Delete:</strong> Los médicos no se borran de la base de datos, se marcan como "inactivos" para mantener la integridad referencial.</li>
<li><strong>Especialidades:</strong> Soporte para Ortopedia, Cardiología, Ginecología y Dermatología.</li>
</ul>

<h3>Gestión de Pacientes</h3>
<ul>
<li><strong>Registro Detallado:</strong> Manejo de datos personales y direcciones mediante objetos incrustados <code>@Embedded</code>.</li>
<li><strong>Validaciones:</strong> Uso de Bean Validation para asegurar que el email, documento y teléfono cumplan con los formatos correctos.</li>
</ul>

<h3>Reserva de Consultas</h3>
<ul>
<li><strong>Agendamiento Inteligente:</strong> Sistema que permite elegir un médico o asignar uno aleatoriamente según la especialidad y disponibilidad en la fecha solicitada.</li>
<li><strong>Cancelamiento:</strong>Gestión de cancelaciones con registro de motivos.</li>
</ul>

<h3>Seguridad y Autenticación</h3>
<ul>
<li><strong>Stateless Authentication:</strong> Implementación de seguridad con Spring Security y <strong>JWT (JSON Web Tokens)</strong>.</li>
<li><strong>Protección de Endpoints:</strong> Uso de <code>@SecurityRequirement</code> para asegurar que solo usuarios autenticados accedan a la gestión médica.</li>
</ul>

<h3>Tecnologías Utilizadas</h3>

<ol>
<li>Java 17</li>
<li>Spring Boot 3</li>
<li>Spring Data JPA (Persistencia)</li>
<li>Spring Security (Autenticación y Autorización)</li>
<li>Flyway (Migraciones de Base de Datos)</li>
<li>PostgreSQL (Base de Datos Relacional)</li>
<li>Lombok (Productividad)</li>
<li>Hibernate Validator (Validaciones de DTOs)</li>
</ol>

<h2> Estructura Destacada</h2>

<h3>Tratamiento de Errores</h3>
<p>
  El proyecto cuenta con un <strong>GestorDeErrores</strong> centralizado que utiliza <code>@RestControllerAdvice</code>. 
  Esto permite capturar excepciones como <strong>404 Not Found</strong>, <strong>400 Bad Request</strong> (validaciones de campos) 
  y errores de autenticación, devolviendo respuestas JSON claras y estandarizadas.
</p>

<h2>Consultas Avanzadas</h2>
<p>
  Se implementaron queries personalizadas en <strong>JPQL</strong> dentro de los Repositorios para lógica compleja, 
  como la selección aleatoria de médicos disponibles:
</p>

```sql
SELECT m FROM Medico m 
WHERE m.activo = true 
AND m.especialidad = :especialidad 
AND m.id NOT IN (SELECT c.medico.id FROM Consulta c WHERE c.fecha = :fecha)
ORDER BY function('random') 
LIMIT 1
```

<h2> Requisitos e Instalación</h2>

<ol>
<li>Clonar el repositorio.</li>
<li>Configurar las credenciales de <strong>PostgreSQL</strong> en </code>src/main/resources/application.properties</code>.</li>
<li>Ejecutar la aplicación (Flyway creará las tablas automáticamente).</li>
<li>Acceder a la documentación en /swagger-ui.html.</li>
</ol>

<p>Proyecto desarrollado como parte de la formación integral de los tres cursos de <strong>Spring Boot 3</strong> en <strong>Alura Latam</strong>.</p>

<h2>Desarrollado por <em>Miguel Ángel de la Cruz Lázaro</em></h2>
