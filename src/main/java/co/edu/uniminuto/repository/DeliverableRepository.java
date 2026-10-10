package co.edu.uniminuto.repository;
//Define que este archivo pertenece al repository

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniminuto.entity.Deliverable;

@Repository
//indica a spring que esta interfaz gestione el acceso a datos 
public interface DeliverableRepository extends JpaRepository<Deliverable, Integer> {
	//Es la clase de entidad mapeada con la tabla de la base de datos 
	//muestra el tipo de dato de la clave  primaria 
}