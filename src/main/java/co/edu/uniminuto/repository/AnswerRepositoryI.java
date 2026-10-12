package co.edu.uniminuto.repository;

import java.util.List;
import co.edu.uniminuto.entity.Answer;

// Define las operaciones de acceso a datos para answers.
public interface AnswerRepositoryI {

    // Registra un nuevo registro.
    Answer insertAnswer(Answer answer);

    // Actualiza un registro existente.
    Answer updateAnswer(Answer answer);

    // Elimina el registro o guarda su estado de baja, segun la entidad.
    int deleteAnswer(Answer answer);

    // Busca un registro por su identificador.
    Answer findByAnswer(int answerId);

    // Consulta todos los registros.
    List<Answer> findAll();
}
