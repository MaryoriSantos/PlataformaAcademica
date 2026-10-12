package co.edu.uniminuto.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uniminuto.entity.Answer;
import co.edu.uniminuto.repository.AnswerRepository;

// Identifica esta clase como componente de la capa de servicio.
@Service
public class AnswerService implements AnswerServiceI {

    // Inyecta el repositorio para acceder a los datos.
    @Autowired
    AnswerRepository answerRepository;

    @Override
    public Answer insertAnswer(Answer answer) {
        // Delega el registro al repositorio.
        return answerRepository.insertAnswer(answer);
    }

    @Override
    public Answer updateAnswer(Answer answer) {
        // Actualiza solamente si el registro ya existe.
        if (answerRepository.findByAnswer(answer.getAnswerId()) != null) {
            return answerRepository.updateAnswer(answer);
        }
        // Retorna null si no existe.
        return null;
    }

    @Override
    public int deleteAnswer(Answer answer) {
                // Busca el registro antes de desactivarlo.
Answer existing = answerRepository.findByAnswer(answer.getAnswerId());
if (existing != null) {
    // Cambia status a cero y guarda el registro sin borrarlo fisicamente.
    existing.setStatus((byte) 0);
    return answerRepository.deleteAnswer(existing);
}
// Retorna cero si no existe.
return 0;
    }

    @Override
    public Answer findByAnswer(int answerId) {
        // Delega la busqueda al repositorio.
        return answerRepository.findByAnswer(answerId);
    }

    @Override
    public List<Answer> findAll() {
        // Delega la consulta al repositorio.
        return answerRepository.findAll();
    }
}
