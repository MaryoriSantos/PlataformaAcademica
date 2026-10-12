package co.edu.uniminuto.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.uniminuto.entity.ResourceRating;
import co.edu.uniminuto.jpa.JpaResourceRating;

// Identifica esta clase como componente de acceso a datos
@Repository
public class ResourceRatingRepository implements ResourceRatingRepositoryI {

    // Inyecta JPA para acceder a la tabla resource_ratings
    @Autowired
    JpaResourceRating jpaResourceRating;

    @Override
    public ResourceRating insertResourceRating(ResourceRating resourceRating) {
        // Guarda la nueva valoracion en la base de datos
        return jpaResourceRating.save(resourceRating);
    }

    @Override
    public ResourceRating updateResourceRating(ResourceRating resourceRating) {
        // Guarda los cambios de la valoracion
        return jpaResourceRating.save(resourceRating);
    }

    @Override
    public int deleteResourceRating(ResourceRating resourceRating) {
        // Busca la valoracion antes de eliminarla
        ResourceRating existingRating = jpaResourceRating
                .findById(resourceRating.getRatingId())
                .orElse(null);

        if (existingRating != null) {
            // Elimina la valoracion encontrada
            jpaResourceRating.delete(existingRating);
            return 1;
        }

        // Retorna cero si la valoracion no existe
        return 0;
    }

    @Override
    public ResourceRating findByResourceRating(int ratingId) {
        // Busca una valoracion por su identificador
        return jpaResourceRating.findById(ratingId).orElse(null);
    }

    @Override
    public List<ResourceRating> findAll() {
        // Consulta todas las valoraciones registradas
        return jpaResourceRating.findAll();
    }
}