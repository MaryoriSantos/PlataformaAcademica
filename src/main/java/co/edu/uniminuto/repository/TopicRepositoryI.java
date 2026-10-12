package co.edu.uniminuto.repository;

import java.util.List;
import co.edu.uniminuto.entity.Topic;

public interface TopicRepositoryI {

    // Registra un nuevo tema
    Topic insertTopic(Topic topic);

    // Actualiza un tema existente
    Topic updateTopic(Topic topic);

    // Elimina un tema
    int deleteTopic(Topic topic);

    // Busca un tema por su identificador
    Topic findByTopic(int topicId);

    // Consulta todos los temas
    List<Topic> findAll();
}