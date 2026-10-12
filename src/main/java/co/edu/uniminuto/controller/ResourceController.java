package co.edu.uniminuto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.uniminuto.entity.Resource;
import co.edu.uniminuto.services.ResourceServiceI;

@RestController
@RequestMapping("/resources")
public class ResourceController {

    // Permite acceder a la logica del servicio
    @Autowired
    ResourceServiceI resourceService;

    // Consulta todos los recursos
    @GetMapping
    public List<Resource> getAllResources() {
        return resourceService.findAll();
    }

    // Consulta un recurso por su identificador
    @GetMapping("/{id}")
    public Resource getResourceById(@PathVariable int id) {
        return resourceService.findByResource(id);
    }

    // Registra un recurso nuevo
    @PostMapping
    public Resource createResource(@RequestBody Resource resource) {
        return resourceService.insertResource(resource);
    }

    // Actualiza un recurso existente
    @PutMapping
    public Resource updateResource(@RequestBody Resource resource) {
        return resourceService.updateResource(resource);
    }

    // Desactiva un recurso sin eliminarlo de la base de datos
    @DeleteMapping
    public int deleteResource(@RequestBody Resource resource) {
        return resourceService.deleteResource(resource);
    }
}