package co.edu.uniminuto.services;

import java.util.List;

import co.edu.uniminuto.entity.ResourceRating;

// Define las operaciones de la capa de servicio para las valoraciones
public interface ResourceRatingServiceI {

    // Registra una nueva valoracion
    ResourceRating insertResourceRating(ResourceRating resourceRating);

    // Actualiza una valoracion existente
    ResourceRating updateResourceRating(ResourceRating resourceRating);

    // Elimina una valoracion
    int deleteResourceRating(ResourceRating resourceRating);

    // Busca una valoracion por su identificador
    ResourceRating findByResourceRating(int ratingId);

    // Consulta todas las valoraciones
    List<ResourceRating> findAll();
}