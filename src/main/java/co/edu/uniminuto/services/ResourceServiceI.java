package co.edu.uniminuto.services;

import java.util.List;
import co.edu.uniminuto.entity.Resource;

public interface ResourceServiceI {

    // Registra un nuevo recurso
    Resource insertResource(Resource resource);

    // Actualiza un recurso existente
    Resource updateResource(Resource resource);

    // Desactiva un recurso
    int deleteResource(Resource resource);

    // Busca un recurso por su identificador
    Resource findByResource(int resourceId);

    // Consulta todos los recursos
    List<Resource> findAll();
}