package co.edu.uniminuto.repository;

import java.util.List;

import co.edu.uniminuto.entity.ResourceRating;

// Define las operaciones para gestionar las valoraciones de recursos
public interface ResourceRatingRepositoryI {

    // Registra una nueva valoracion
    ResourceRating insertResourceRating(ResourceRating resourceRating);

    // Actualiza una valoracion existente
    ResourceRating updateResourceRating(ResourceRating resourceRating);

    // Elimina una valoracion
    int deleteResourceRating(ResourceRating resourceRating);

    // Busca una valoracion por su identificador
    ResourceRating findByResourceRating(int ratingId);

    // Consulta todas las valoraciones registradas
    List<ResourceRating> findAll();
}