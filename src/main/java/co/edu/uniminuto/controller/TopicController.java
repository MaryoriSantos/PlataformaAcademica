package co.edu.uniminuto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.uniminuto.entity.Topic;
import co.edu.uniminuto.services.TopicServiceI;

@RestController
@RequestMapping("/topics")
public class TopicController {

    // Permite acceder a la logica del servicio
    @Autowired
    TopicServiceI topicService;

    // Consulta todos los temas
    @GetMapping
    public List<Topic> getAllTopics() {
        return topicService.findAll();
    }

    // Consulta un tema por su identificador
    @GetMapping("/{id}")
    public Topic getTopicById(@PathVariable int id) {
        return topicService.findByTopic(id);
    }

    // Registra un tema nuevo
    @PostMapping
    public Topic createTopic(@RequestBody Topic topic) {
        return topicService.insertTopic(topic);
    }

    // Actualiza un tema existente
    @PutMapping
    public Topic updateTopic(@RequestBody Topic topic) {
        return topicService.updateTopic(topic);
    }

    // Elimina un tema por medio de su identificador
    @DeleteMapping
    public int deleteTopic(@RequestBody Topic topic) {
        return topicService.deleteTopic(topic);
    }
}