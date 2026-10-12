package co.edu.uniminuto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniminuto.entity.Resource;
import co.edu.uniminuto.repository.ResourceRepository;

@Service
public class ResourceService implements ResourceServiceI {

    // Permite utilizar las operaciones del repositorio
    @Autowired
    ResourceRepository resourceRepository;

    @Override
    public Resource insertResource(Resource resource) {
        // Solicita al repositorio guardar el recurso
        return resourceRepository.insertResource(resource);
    }

    @Override
    public Resource updateResource(Resource resource) {
        // Comprueba que el recurso exista antes de actualizarlo
        if (resourceRepository.findByResource(resource.getResourceId()) != null) {
            return resourceRepository.updateResource(resource);
        }

        // Retorna null si no encuentra el recurso
        return null;
    }

    @Override
    public int deleteResource(Resource resource) {
        // Comprueba que el recurso exista antes de desactivarlo
        if (resourceRepository.findByResource(resource.getResourceId()) != null) {
            return resourceRepository.deleteResource(resource);
        }

        return 0;
    }

    @Override
    public Resource findByResource(int resourceId) {
        // Delega la busqueda al repositorio
        return resourceRepository.findByResource(resourceId);
    }

    @Override
    public List<Resource> findAll() {
        // Delega la consulta de todos los recursos al repositorio
        return resourceRepository.findAll();
    }
}