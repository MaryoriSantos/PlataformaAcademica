package co.edu.uniminuto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniminuto.entity.ResourceRating;
import co.edu.uniminuto.repository.ResourceRatingRepository;

// Identifica esta clase como componente de la capa de servicio
@Service
public class ResourceRatingService implements ResourceRatingServiceI {

    // Inyecta el repositorio para acceder a los datos
    @Autowired
    ResourceRatingRepository resourceRatingRepository;

    @Override
    public ResourceRating insertResourceRating(ResourceRating resourceRating) {
        // Solicita al repositorio registrar la valoracion
        return resourceRatingRepository.insertResourceRating(resourceRating);
    }

    @Override
    public ResourceRating updateResourceRating(ResourceRating resourceRating) {
        // Comprueba que la valoracion exista antes de actualizarla
        if (resourceRatingRepository.findByResourceRating(
                resourceRating.getRatingId()) != null) {

            return resourceRatingRepository.updateResourceRating(resourceRating);
        }

        // Retorna null si la valoracion no existe
        return null;
    }

    @Override
    public int deleteResourceRating(ResourceRating resourceRating) {
        // Comprueba que la valoracion exista antes de eliminarla
        if (resourceRatingRepository.findByResourceRating(
                resourceRating.getRatingId()) != null) {

            return resourceRatingRepository.deleteResourceRating(resourceRating);
        }

        // Retorna cero si la valoracion no existe
        return 0;
    }

    @Override
    public ResourceRating findByResourceRating(int ratingId) {
        // Delega la busqueda al repositorio
        return resourceRatingRepository.findByResourceRating(ratingId);
    }

    @Override
    public List<ResourceRating> findAll() {
        // Delega la consulta de todas las valoraciones
        return resourceRatingRepository.findAll();
    }
}