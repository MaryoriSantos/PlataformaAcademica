package co.edu.uniminuto.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uniminuto.entity.TopicDifficulty;
import co.edu.uniminuto.repository.TopicDifficultyRepository;

// Identifica esta clase como componente de la capa de servicio.
@Service
public class TopicDifficultyService implements TopicDifficultyServiceI {

    // Inyecta el repositorio para acceder a los datos.
    @Autowired
    TopicDifficultyRepository topicDifficultyRepository;

    @Override
    public TopicDifficulty insertTopicDifficulty(TopicDifficulty topicDifficulty) {
        // Delega el registro al repositorio.
        return topicDifficultyRepository.insertTopicDifficulty(topicDifficulty);
    }

    @Override
    public TopicDifficulty updateTopicDifficulty(TopicDifficulty topicDifficulty) {
        // Actualiza solamente si el registro ya existe.
        if (topicDifficultyRepository.findByTopicDifficulty(topicDifficulty.getTopicDifficultyId()) != null) {
            return topicDifficultyRepository.updateTopicDifficulty(topicDifficulty);
        }
        // Retorna null si no existe.
        return null;
    }

    @Override
    public int deleteTopicDifficulty(TopicDifficulty topicDifficulty) {
                // Comprueba que el registro exista antes de eliminarlo.
if (topicDifficultyRepository.findByTopicDifficulty(topicDifficulty.getTopicDifficultyId()) != null) {
    return topicDifficultyRepository.deleteTopicDifficulty(topicDifficulty);
}
// Retorna cero si no existe.
return 0;
    }

    @Override
    public TopicDifficulty findByTopicDifficulty(int topicDifficultyId) {
        // Delega la busqueda al repositorio.
        return topicDifficultyRepository.findByTopicDifficulty(topicDifficultyId);
    }

    @Override
    public List<TopicDifficulty> findAll() {
        // Delega la consulta al repositorio.
        return topicDifficultyRepository.findAll();
    }
}
