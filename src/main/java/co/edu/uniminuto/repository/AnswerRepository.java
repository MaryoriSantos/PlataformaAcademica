package co.edu.uniminuto.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import co.edu.uniminuto.entity.Answer;
import co.edu.uniminuto.jpa.JpaAnswer;

// Identifica esta clase como componente de acceso a datos.
@Repository
public class AnswerRepository implements AnswerRepositoryI {

    // Inyecta JPA para acceder a la tabla correspondiente.
    @Autowired
    JpaAnswer jpaAnswer;

    @Override
    public Answer insertAnswer(Answer answer) {
        // Guarda un registro nuevo.
        return jpaAnswer.save(answer);
    }

    @Override
    public Answer updateAnswer(Answer answer) {
        // Guarda los cambios del registro.
        return jpaAnswer.save(answer);
    }

    @Override
    public int deleteAnswer(Answer answer) {
                // Guarda el registro con status = 0 para desactivarlo sin borrarlo fisicamente.
Answer saved = jpaAnswer.save(answer);
return saved != null ? 1 : 0;
    }

    @Override
    public Answer findByAnswer(int answerId) {
        // Busca por identificador y retorna null si no existe.
        return jpaAnswer.findById(answerId).orElse(null);
    }

    @Override
    public List<Answer> findAll() {
        // Retorna todos los registros de la tabla.
        return jpaAnswer.findAll();
    }
}
