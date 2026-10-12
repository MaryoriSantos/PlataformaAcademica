package co.edu.uniminuto.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import co.edu.uniminuto.entity.TopicDifficulty;
import co.edu.uniminuto.services.TopicDifficultyServiceI;

// Identifica esta clase como controlador REST.
@RestController
// Define la ruta base del modulo.
@RequestMapping("/topic-difficulties")
public class TopicDifficultyController {

    // Inyecta el servicio que contiene la logica del modulo.
    @Autowired
    TopicDifficultyServiceI topicDifficultyService;

    // Consulta todos los registros.
    @GetMapping
    public List<TopicDifficulty> getAllTopicDifficultys() {
        return topicDifficultyService.findAll();
    }

    // Consulta un registro por su identificador.
    @GetMapping("/{id}")
    public TopicDifficulty getTopicDifficultyById(@PathVariable int id) {
        return topicDifficultyService.findByTopicDifficulty(id);
    }

    // Registra un nuevo registro a partir del JSON recibido.
    @PostMapping
    public TopicDifficulty createTopicDifficulty(@RequestBody TopicDifficulty topicDifficulty) {
        return topicDifficultyService.insertTopicDifficulty(topicDifficulty);
    }

    // Actualiza un registro existente.
    @PutMapping
    public TopicDifficulty updateTopicDifficulty(@RequestBody TopicDifficulty topicDifficulty) {
        return topicDifficultyService.updateTopicDifficulty(topicDifficulty);
    }

    // Elimina o desactiva un registro usando el JSON recibido.
    @DeleteMapping
    public int deleteTopicDifficulty(@RequestBody TopicDifficulty topicDifficulty) {
        return topicDifficultyService.deleteTopicDifficulty(topicDifficulty);
    }
}
