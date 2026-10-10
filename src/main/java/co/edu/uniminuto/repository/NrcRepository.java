package co.edu.uniminuto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniminuto.entity.Nrc;

@Repository
//le indica a sprig que este componente es el encargado de conectarse con la tabla de la base de datos 
public interface NrcRepository extends JpaRepository<Nrc, Integer> {
	//hereda los CRUD, la clase de identidad vinculada y el tipo de dato de la clave primaria 
}