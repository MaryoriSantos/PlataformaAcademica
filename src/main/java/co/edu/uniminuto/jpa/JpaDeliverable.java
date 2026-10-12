package co.edu.uniminuto.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.uniminuto.entity.Deliverable;

public interface JpaDeliverable extends JpaRepository<Deliverable, Integer> {
}