package co.edu.uniminuto.services;

import java.util.List;
import co.edu.uniminuto.entity.Answer;

// Define las operaciones de negocio disponibles para answers.
public interface AnswerServiceI {

    // Registra un nuevo registro.
    Answer insertAnswer(Answer answer);

    // Actualiza un registro existente.
    Answer updateAnswer(Answer answer);

    // Elimina o desactiva un registro.
    int deleteAnswer(Answer answer);

    // Busca un registro por identificador.
    Answer findByAnswer(int answerId);

    // Consulta todos los registros.
    List<Answer> findAll();
}
