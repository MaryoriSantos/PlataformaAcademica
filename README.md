# Plataforma Académica

## Descripción

Plataforma Académica es un proyecto desarrollado para el curso de
Programación Avanzada. Su propósito es centralizar y gestionar
información relacionada con la actividad académica de los estudiantes,
organizando funcionalidades alrededor de asignaturas, NRC, entregables,
recursos, preguntas, calificaciones y recomendaciones.

El proyecto se desarrolla mediante una API REST con una arquitectura
organizada por capas y utiliza MySQL como sistema de gestión de base de
datos.

## Objetivo

Desarrollar una plataforma que permita gestionar información académica
de los estudiantes y facilitar el acceso y organización de los
diferentes recursos y actividades asociados a sus NRC.

El proyecto busca apoyar la organización del trabajo académico y está
relacionado con el ODS 4: Educación de calidad.

## Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- JPA / Hibernate
- MySQL
- MySQL Workbench
- Postman
- Git y GitHub
- Docker, contemplado para la ejecución y despliegue del proyecto

## Arquitectura del proyecto

El proyecto está organizado siguiendo una arquitectura por capas:

- Controller: recibe y gestiona las solicitudes HTTP de la API REST.
- Service: contiene la lógica de servicio de la aplicación.
- Repository: gestiona el acceso a los datos.
- JPA: contiene las interfaces utilizadas para trabajar con Spring Data
  JPA.
- Entity: contiene las entidades que representan las tablas de la base
  de datos.

## Estructura del proyecto

``` text
src/main/java/co/edu/uniminuto
├── controller
├── entity
├── jpa
├── repository
└── services
```

## Base de datos

La base de datos utilizada por el proyecto es:

``` text
plataforma_academica
```

El repositorio incluye una copia de la estructura y los datos de prueba
mediante:

``` text
database/plataforma_academica.sql
```

Cada integrante puede trabajar con su propia instancia local de MySQL
utilizando la misma estructura de base de datos.

## Módulo de usuarios

Funcionalidades implementadas:

- Consultar todos los usuarios.
- Consultar un usuario por ID.
- Consultar un usuario por correo electrónico.
- Registrar un usuario.
- Actualizar un usuario.
- Manejar el estado del usuario mediante el campo status.

Endpoints disponibles:

``` text
GET  /user
GET  /user/id?id={id}
GET  /user/email?email={email}
POST /user
PUT  /user
```

## Módulo de roles

Funcionalidades implementadas:

- Consultar todos los roles.
- Consultar un rol por ID.
- Registrar un rol.
- Actualizar un rol.

Endpoints disponibles:

``` text
GET  /role
GET  /role/id?id={id}
POST /role
PUT /role
```

## Pruebas con Postman

Las pruebas de la API REST se realizan mediante Postman.

La colección se encuentra en:

``` text
postman/PlataformaAcademica.postman_collection.json
```

## Configuración de la aplicación

La configuración de conexión se encuentra en:

``` text
src/main/resources/application.properties
```

Cada integrante debe configurar sus propias credenciales locales de
MySQL.

No se deben publicar contraseñas reales en GitHub.

## Ejecución del proyecto

1.  Tener instalado Java 21.
2.  Tener instalado MySQL.
3.  Crear o importar la base de datos `plataforma_academica`.
4.  Configurar las credenciales locales de MySQL.
5.  Abrir el proyecto desde Eclipse o el IDE utilizado.
6.  Ejecutar `PlataformaAcademicaApplication`.
7.  Verificar la aplicación en:

``` text
http://localhost:8080
```

8.  Ejecutar las pruebas mediante Postman.

## Trabajo colaborativo

El proyecto utiliza Git y GitHub para compartir el código fuente y los
recursos necesarios para el desarrollo.

El repositorio contiene, entre otros elementos:

``` text
database/
postman/
src/
pom.xml
README.md
```

La base de datos se comparte mediante el archivo SQL y las pruebas de
API mediante la colección de Postman.

## Módulos del proyecto

El proyecto contempla módulos relacionados con:

- Usuarios y roles
- Asignaturas
- NRC
- Inscripciones a NRC
- Entregables
- Calificaciones
- Recursos académicos
- Temas
- Preguntas y respuestas
- Notificaciones
- Recomendaciones mediante inteligencia artificial

Los módulos se implementarán e integrarán progresivamente.

## Estado actual

Actualmente se encuentran implementados y probados:

- Conexión de Spring Boot con MySQL.
- Arquitectura por capas.
- Entidad User.
- Entidad Role.
- Repositorios.
- Servicios.
- Controladores REST.
- Consultas por ID.
- Consulta por correo electrónico para usuarios.
- Registro y actualización mediante POST y PUT.
- Pruebas mediante Postman.
- Exportación de la base de datos.
- Colección de Postman para compartir las pruebas.

Los demás módulos serán desarrollados e integrados posteriormente.

## Integrantes

- Maryori
- Wilmer
- Miguel

## Licencia

Proyecto académico desarrollado con fines educativos.
