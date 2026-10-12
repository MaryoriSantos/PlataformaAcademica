package co.edu.uniminuto.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.uniminuto.entity.Topic;

// Proporciona las operaciones de acceso a la tabla topics
public interface JpaTopic extends JpaRepository<Topic, Integer> {

}