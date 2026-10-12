package co.edu.uniminuto.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uniminuto.entity.Resource;

// Proporciona las operaciones de acceso a la tabla resources
public interface JpaResource extends JpaRepository<Resource, Integer> {

}