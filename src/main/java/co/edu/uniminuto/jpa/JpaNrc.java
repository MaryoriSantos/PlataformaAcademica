package co.edu.uniminuto.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.uniminuto.entity.Nrc;

public interface JpaNrc extends JpaRepository<Nrc, Integer> {
}