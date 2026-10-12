package co.edu.uniminuto.services;

import java.util.List;
import co.edu.uniminuto.entity.TopicDifficulty;

// Define las operaciones de negocio disponibles para topic difficulties.
public interface TopicDifficultyServiceI {

    // Registra un nuevo registro.
    TopicDifficulty insertTopicDifficulty(TopicDifficulty topicDifficulty);

    // Actualiza un registro existente.
    TopicDifficulty updateTopicDifficulty(TopicDifficulty topicDifficulty);

    // Elimina o desactiva un registro.
    int deleteTopicDifficulty(TopicDifficulty topicDifficulty);

    // Busca un registro por identificador.
    TopicDifficulty findByTopicDifficulty(int topicDifficultyId);

    // Consulta todos los registros.
    List<TopicDifficulty> findAll();
}
