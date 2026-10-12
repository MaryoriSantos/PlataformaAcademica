package co.edu.uniminuto.repository;

import java.util.List;
import co.edu.uniminuto.entity.TopicDifficulty;

// Define las operaciones de acceso a datos para topic difficulties.
public interface TopicDifficultyRepositoryI {

    // Registra un nuevo registro.
    TopicDifficulty insertTopicDifficulty(TopicDifficulty topicDifficulty);

    // Actualiza un registro existente.
    TopicDifficulty updateTopicDifficulty(TopicDifficulty topicDifficulty);

    // Elimina el registro o guarda su estado de baja, segun la entidad.
    int deleteTopicDifficulty(TopicDifficulty topicDifficulty);

    // Busca un registro por su identificador.
    TopicDifficulty findByTopicDifficulty(int topicDifficultyId);

    // Consulta todos los registros.
    List<TopicDifficulty> findAll();
}
