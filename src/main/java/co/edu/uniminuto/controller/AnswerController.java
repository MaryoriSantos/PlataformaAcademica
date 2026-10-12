package co.edu.uniminuto.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import co.edu.uniminuto.entity.Answer;
import co.edu.uniminuto.services.AnswerServiceI;

// Identifica esta clase como controlador REST.
@RestController
// Define la ruta base del modulo.
@RequestMapping("/answers")
public class AnswerController {

    // Inyecta el servicio que contiene la logica del modulo.
    @Autowired
    AnswerServiceI answerService;

    // Consulta todos los registros.
    @GetMapping
    public List<Answer> getAllAnswers() {
        return answerService.findAll();
    }

    // Consulta un registro por su identificador.
    @GetMapping("/{id}")
    public Answer getAnswerById(@PathVariable int id) {
        return answerService.findByAnswer(id);
    }

    // Registra un nuevo registro a partir del JSON recibido.
    @PostMapping
    public Answer createAnswer(@RequestBody Answer answer) {
        return answerService.insertAnswer(answer);
    }

    // Actualiza un registro existente.
    @PutMapping
    public Answer updateAnswer(@RequestBody Answer answer) {
        return answerService.updateAnswer(answer);
    }

    // Elimina o desactiva un registro usando el JSON recibido.
    @DeleteMapping
    public int deleteAnswer(@RequestBody Answer answer) {
        return answerService.deleteAnswer(answer);
    }
}
