package co.edu.uniminuto.services;

import java.util.List;
import co.edu.uniminuto.entity.Topic;

public interface TopicServiceI {

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