package co.edu.uniminuto.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.uniminuto.entity.AiRecomendation;

// Proporciona las operaciones CRUD para la entidad AiRecomendation.
public interface JpaAiRecomendation extends JpaRepository<AiRecomendation, Integer> {

}
