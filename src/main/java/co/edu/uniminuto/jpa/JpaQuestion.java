package co.edu.uniminuto.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.uniminuto.entity.Question;

// Proporciona las operaciones CRUD para la entidad Question.
public interface JpaQuestion extends JpaRepository<Question, Integer> {

}
