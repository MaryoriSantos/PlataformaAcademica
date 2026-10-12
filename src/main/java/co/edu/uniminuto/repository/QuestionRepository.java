package co.edu.uniminuto.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import co.edu.uniminuto.entity.Question;
import co.edu.uniminuto.jpa.JpaQuestion;

// Identifica esta clase como componente de acceso a datos.
@Repository
public class QuestionRepository implements QuestionRepositoryI {

    // Inyecta JPA para acceder a la tabla correspondiente.
    @Autowired
    JpaQuestion jpaQuestion;

    @Override
    public Question insertQuestion(Question question) {
        // Guarda un registro nuevo.
        return jpaQuestion.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        // Guarda los cambios del registro.
        return jpaQuestion.save(question);
    }

    @Override
    public int deleteQuestion(Question question) {
                // Guarda el registro con status = 0 para desactivarlo sin borrarlo fisicamente.
Question saved = jpaQuestion.save(question);
return saved != null ? 1 : 0;
    }

    @Override
    public Question findByQuestion(int questionId) {
        // Busca por identificador y retorna null si no existe.
        return jpaQuestion.findById(questionId).orElse(null);
    }

    @Override
    public List<Question> findAll() {
        // Retorna todos los registros de la tabla.
        return jpaQuestion.findAll();
    }
}
