package co.edu.uniminuto.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.uniminuto.entity.TopicDifficulty;

// Proporciona las operaciones CRUD para la entidad TopicDifficulty.
public interface JpaTopicDifficulty extends JpaRepository<TopicDifficulty, Integer> {

}
