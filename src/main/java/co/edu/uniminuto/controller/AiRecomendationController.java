package co.edu.uniminuto.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import co.edu.uniminuto.entity.AiRecomendation;
import co.edu.uniminuto.services.AiRecomendationServiceI;

// Identifica esta clase como controlador REST.
@RestController
// Define la ruta base del modulo.
@RequestMapping("/ai-recomendations")
public class AiRecomendationController {

    // Inyecta el servicio que contiene la logica del modulo.
    @Autowired
    AiRecomendationServiceI aiRecomendationService;

    // Consulta todos los registros.
    @GetMapping
    public List<AiRecomendation> getAllAiRecomendations() {
        return aiRecomendationService.findAll();
    }

    // Consulta un registro por su identificador.
    @GetMapping("/{id}")
    public AiRecomendation getAiRecomendationById(@PathVariable int id) {
        return aiRecomendationService.findByAiRecomendation(id);
    }

    // Registra un nuevo registro a partir del JSON recibido.
    @PostMapping
    public AiRecomendation createAiRecomendation(@RequestBody AiRecomendation aiRecomendation) {
        return aiRecomendationService.insertAiRecomendation(aiRecomendation);
    }

    // Actualiza un registro existente.
    @PutMapping
    public AiRecomendation updateAiRecomendation(@RequestBody AiRecomendation aiRecomendation) {
        return aiRecomendationService.updateAiRecomendation(aiRecomendation);
    }

    // Elimina o desactiva un registro usando el JSON recibido.
    @DeleteMapping
    public int deleteAiRecomendation(@RequestBody AiRecomendation aiRecomendation) {
        return aiRecomendationService.deleteAiRecomendation(aiRecomendation);
    }
}
