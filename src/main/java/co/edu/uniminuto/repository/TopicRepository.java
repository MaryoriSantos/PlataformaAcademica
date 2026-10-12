package co.edu.uniminuto.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.uniminuto.entity.Topic;
import co.edu.uniminuto.jpa.JpaTopic;

@Repository
public class TopicRepository implements TopicRepositoryI {

    // Permite acceder a las operaciones de JPA para Topic
    @Autowired
    JpaTopic jpaTopic;

    @Override
    public Topic insertTopic(Topic topic) {
        // Guarda el tema en la base de datos
        return jpaTopic.save(topic);
    }

    @Override
    public Topic updateTopic(Topic topic) {
        // Guarda los cambios del tema
        return jpaTopic.save(topic);
    }

    @Override
    public int deleteTopic(Topic topic) {
        // Busca el tema antes de eliminarlo
        Topic existingTopic = jpaTopic.findById(topic.getTopicId())
                .orElse(null);

        if (existingTopic != null) {
            // Elimina el tema encontrado
            jpaTopic.delete(existingTopic);
            return 1;
        }

        // Indica que el tema no existe
        return 0;
    }

    @Override
    public Topic findByTopic(int topicId) {
        // Busca un tema por su identificador
        return jpaTopic.findById(topicId).orElse(null);
    }

    @Override
    public List<Topic> findAll() {
        // Consulta todos los temas registrados
        return jpaTopic.findAll();
    }
}