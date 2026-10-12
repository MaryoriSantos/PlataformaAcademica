package co.edu.uniminuto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.uniminuto.entity.ResourceRating;
import co.edu.uniminuto.services.ResourceRatingServiceI;

// Identifica esta clase como controlador REST
@RestController

// Define la ruta base del modulo de valoraciones
@RequestMapping("/resource-ratings")
public class ResourceRatingController {

    // Inyecta el servicio encargado de la logica de las valoraciones
    @Autowired
    ResourceRatingServiceI resourceRatingService;

    // Consulta todas las valoraciones registradas
    @GetMapping
    public List<ResourceRating> getAllResourceRatings() {
        return resourceRatingService.findAll();
    }

    // Busca una valoracion por su identificador
    @GetMapping("/{id}")
    public ResourceRating getResourceRatingById(@PathVariable int id) {
        return resourceRatingService.findByResourceRating(id);
    }

    // Registra una nueva valoracion
    @PostMapping
    public ResourceRating createResourceRating(
            @RequestBody ResourceRating resourceRating) {

        return resourceRatingService.insertResourceRating(resourceRating);
    }

    // Actualiza una valoracion existente
    @PutMapping
    public ResourceRating updateResourceRating(
            @RequestBody ResourceRating resourceRating) {

        return resourceRatingService.updateResourceRating(resourceRating);
    }

    // Elimina una valoracion mediante los datos recibidos
    @DeleteMapping
    public int deleteResourceRating(
            @RequestBody ResourceRating resourceRating) {

        return resourceRatingService.deleteResourceRating(resourceRating);
    }
}