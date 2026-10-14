# Etapa 1: compilar el proyecto con Java 21 y Maven
FROM maven:3.9-eclipse-temurin-21 AS build

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo de configuracion de Maven
COPY pom.xml .

# Copiar el codigo fuente de Spring Boot
COPY src ./src

# Compilar el proyecto y generar el archivo JAR
RUN mvn -B -DskipTests clean package

# Etapa 2: preparar el entorno para ejecutar la aplicacion
FROM eclipse-temurin:21-jre

# Directorio donde se ejecutara la aplicacion
WORKDIR /app

# Copiar el JAR generado durante la compilacion
COPY --from=build /app/target/*.jar app.jar

# Puerto que utiliza Spring Boot dentro del contenedor
EXPOSE 8080

# Comando para iniciar la aplicacion
ENTRYPOINT ["java", "-jar", "app.jar"]