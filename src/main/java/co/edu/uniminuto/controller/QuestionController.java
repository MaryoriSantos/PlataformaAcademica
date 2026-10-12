package co.edu.uniminuto.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import co.edu.uniminuto.entity.Question;
import co.edu.uniminuto.services.QuestionServiceI;

// Identifica esta clase como controlador REST.
@RestController
// Define la ruta base del modulo.
@RequestMapping("/questions")
public class QuestionController {

    // Inyecta el servicio que contiene la logica del modulo.
    @Autowired
    QuestionServiceI questionService;

    // Consulta todos los registros.
    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.findAll();
    }

    // Consulta un registro por su identificador.
    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable int id) {
        return questionService.findByQuestion(id);
    }

    // Registra un nuevo registro a partir del JSON recibido.
    @PostMapping
    public Question createQuestion(@RequestBody Question question) {
        return questionService.insertQuestion(question);
    }

    // Actualiza un registro existente.
    @PutMapping
    public Question updateQuestion(@RequestBody Question question) {
        return questionService.updateQuestion(question);
    }

    // Elimina o desactiva un registro usando el JSON recibido.
    @DeleteMapping
    public int deleteQuestion(@RequestBody Question question) {
        return questionService.deleteQuestion(question);
    }
}
