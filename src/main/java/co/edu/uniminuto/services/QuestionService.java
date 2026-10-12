package co.edu.uniminuto.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uniminuto.entity.Question;
import co.edu.uniminuto.repository.QuestionRepository;

// Identifica esta clase como componente de la capa de servicio.
@Service
public class QuestionService implements QuestionServiceI {

    // Inyecta el repositorio para acceder a los datos.
    @Autowired
    QuestionRepository questionRepository;

    @Override
    public Question insertQuestion(Question question) {
        // Delega el registro al repositorio.
        return questionRepository.insertQuestion(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        // Actualiza solamente si el registro ya existe.
        if (questionRepository.findByQuestion(question.getQuestionId()) != null) {
            return questionRepository.updateQuestion(question);
        }
        // Retorna null si no existe.
        return null;
    }

    @Override
    public int deleteQuestion(Question question) {
                // Busca el registro antes de desactivarlo.
Question existing = questionRepository.findByQuestion(question.getQuestionId());
if (existing != null) {
    // Cambia status a cero y guarda el registro sin borrarlo fisicamente.
    existing.setStatus((byte) 0);
    return questionRepository.deleteQuestion(existing);
}
// Retorna cero si no existe.
return 0;
    }

    @Override
    public Question findByQuestion(int questionId) {
        // Delega la busqueda al repositorio.
        return questionRepository.findByQuestion(questionId);
    }

    @Override
    public List<Question> findAll() {
        // Delega la consulta al repositorio.
        return questionRepository.findAll();
    }
}
