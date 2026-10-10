package co.edu.uniminuto.repository;
//Define que este archivo pertenece al paquete Repository

import org.springframework.data.jpa.repository.JpaRepository;
//Importa la interfaz generica del spring Data JPA que contiene los metodos CRUD 
import org.springframework.stereotype.Repository;
//importa la anotacion que le indica a spring que esta interfaz maneja el acceso a la capa de datos

import co.edu.uniminuto.entity.Grade;
//importa le entidad que representa la tabla de calificaciones en la base de datos 

@Repository
//señala a spring que debe registtrar esta interfaz como un Bean de persistencia dentro del contenedor de dependencias 
public interface GradeRepository extends JpaRepository<Grade, Integer> {
	//Se define como una interfaz y no como una clase 
	//muestra la entidad mapeada con la tabla de la base de datos  y muestra el tipo de dato de la clave primaria 
}