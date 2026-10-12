package co.edu.uniminuto.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import co.edu.uniminuto.entity.TopicDifficulty;
import co.edu.uniminuto.jpa.JpaTopicDifficulty;

// Identifica esta clase como componente de acceso a datos.
@Repository
public class TopicDifficultyRepository implements TopicDifficultyRepositoryI {

    // Inyecta JPA para acceder a la tabla correspondiente.
    @Autowired
    JpaTopicDifficulty jpaTopicDifficulty;

    @Override
    public TopicDifficulty insertTopicDifficulty(TopicDifficulty topicDifficulty) {
        // Guarda un registro nuevo.
        return jpaTopicDifficulty.save(topicDifficulty);
    }

    @Override
    public TopicDifficulty updateTopicDifficulty(TopicDifficulty topicDifficulty) {
        // Guarda los cambios del registro.
        return jpaTopicDifficulty.save(topicDifficulty);
    }

    @Override
    public int deleteTopicDifficulty(TopicDifficulty topicDifficulty) {
                // Busca el registro antes de eliminarlo fisicamente.
TopicDifficulty existing = jpaTopicDifficulty.findById(topicDifficulty.getTopicDifficultyId()).orElse(null);
if (existing != null) {
    jpaTopicDifficulty.delete(existing);
    return 1;
}
// Retorna cero si el registro no existe.
return 0;
    }

    @Override
    public TopicDifficulty findByTopicDifficulty(int topicDifficultyId) {
        // Busca por identificador y retorna null si no existe.
        return jpaTopicDifficulty.findById(topicDifficultyId).orElse(null);
    }

    @Override
    public List<TopicDifficulty> findAll() {
        // Retorna todos los registros de la tabla.
        return jpaTopicDifficulty.findAll();
    }
}
