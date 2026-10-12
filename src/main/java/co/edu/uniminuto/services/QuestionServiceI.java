package co.edu.uniminuto.services;

import java.util.List;
import co.edu.uniminuto.entity.Question;

// Define las operaciones de negocio disponibles para questions.
public interface QuestionServiceI {

    // Registra un nuevo registro.
    Question insertQuestion(Question question);

    // Actualiza un registro existente.
    Question updateQuestion(Question question);

    // Elimina o desactiva un registro.
    int deleteQuestion(Question question);

    // Busca un registro por identificador.
    Question findByQuestion(int questionId);

    // Consulta todos los registros.
    List<Question> findAll();
}
