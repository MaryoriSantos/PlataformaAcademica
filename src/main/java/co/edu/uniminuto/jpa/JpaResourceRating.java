package co.edu.uniminuto.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uniminuto.entity.ResourceRating;

// Proporciona las operaciones CRUD para las valoraciones de recursos
public interface JpaResourceRating extends JpaRepository<ResourceRating, Integer> {

}