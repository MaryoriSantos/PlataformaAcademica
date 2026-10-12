package co.edu.uniminuto.repository;

import java.util.List;
import co.edu.uniminuto.entity.Resource;

public interface ResourceRepositoryI {

    // Registra un nuevo recurso
    Resource insertResource(Resource resource);

    // Actualiza la informacion de un recurso
    Resource updateResource(Resource resource);

    // Desactiva un recurso registrado
    int deleteResource(Resource resource);

    // Busca un recurso por su identificador
    Resource findByResource(int resourceId);

    // Consulta todos los recursos
    List<Resource> findAll();
}