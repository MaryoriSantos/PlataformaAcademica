package co.edu.uniminuto.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.uniminuto.entity.Answer;

// Proporciona las operaciones CRUD para la entidad Answer.
public interface JpaAnswer extends JpaRepository<Answer, Integer> {

}
