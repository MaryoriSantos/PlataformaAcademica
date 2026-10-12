package co.edu.uniminuto.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.uniminuto.entity.Resource;
import co.edu.uniminuto.jpa.JpaResource;

@Repository
public class ResourceRepository implements ResourceRepositoryI {

    // Permite acceder a las operaciones de JPA para Resource
    @Autowired
    JpaResource jpaResource;

    @Override
    public Resource insertResource(Resource resource) {
        // Guarda el nuevo recurso en la base de datos
        return jpaResource.save(resource);
    }

    @Override
    public Resource updateResource(Resource resource) {
        // Guarda los cambios del recurso
        return jpaResource.save(resource);
    }

    @Override
    public int deleteResource(Resource resource) {
        // Busca el recurso antes de desactivarlo
        Resource existingResource = jpaResource
                .findById(resource.getResourceId())
                .orElse(null);

        if (existingResource != null) {
            // Cambia el estado a cero sin borrar el registro
            existingResource.setStatus((byte) 0);
            jpaResource.save(existingResource);
            return 1;
        }

        // Retorna cero si el recurso no existe
        return 0;
    }

    @Override
    public Resource findByResource(int resourceId) {
        // Busca el recurso por su identificador
        return jpaResource.findById(resourceId).orElse(null);
    }

    @Override
    public List<Resource> findAll() {
        // Retorna todos los recursos registrados
        return jpaResource.findAll();
    }
}