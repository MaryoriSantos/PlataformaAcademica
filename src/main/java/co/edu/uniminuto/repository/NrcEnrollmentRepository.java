package co.edu.uniminuto.repository;
//Ubica la interfaz dentro del paquete 

import org.springframework.data.jpa.repository.JpaRepository;
//Importa la interfaz que contiene los metodos de consulta

import org.springframework.stereotype.Repository;
//Importa la anotacion que le dice por el framework que este componente maneja la base de datos 

import co.edu.uniminuto.entity.NrcEnrollment;
//Importa la entidad java que esta mapeada con la tabla


@Repository
//le dce a spring que debe gestionar esta interfaz como un Bean

public interface NrcEnrollmentRepository extends JpaRepository<NrcEnrollment, Integer> {
	//se define como una interfaz
	//indiamos la entidad sobre la cual se van a hacer las operaciones y el tipo de dato de la clave primaria 
}