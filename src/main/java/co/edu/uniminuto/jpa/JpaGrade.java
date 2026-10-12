package co.edu.uniminuto.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.uniminuto.entity.Grade;

public interface JpaGrade extends JpaRepository<Grade, Integer> {
}