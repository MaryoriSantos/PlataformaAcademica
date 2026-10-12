package co.edu.uniminuto.repository;

import java.util.List;
import co.edu.uniminuto.entity.Question;

// Define las operaciones de acceso a datos para questions.
public interface QuestionRepositoryI {

    // Registra un nuevo registro.
    Question insertQuestion(Question question);

    // Actualiza un registro existente.
    Question updateQuestion(Question question);

    // Elimina el registro o guarda su estado de baja, segun la entidad.
    int deleteQuestion(Question question);

    // Busca un registro por su identificador.
    Question findByQuestion(int questionId);

    // Consulta todos los registros.
    List<Question> findAll();
}
