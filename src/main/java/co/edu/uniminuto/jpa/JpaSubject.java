package co.edu.uniminuto.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.uniminuto.entity.Subject;

public interface JpaSubject extends JpaRepository<Subject, Integer> {
}