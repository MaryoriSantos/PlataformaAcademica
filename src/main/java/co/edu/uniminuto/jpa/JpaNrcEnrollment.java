package co.edu.uniminuto.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.uniminuto.entity.NrcEnrollment;

public interface JpaNrcEnrollment extends JpaRepository<NrcEnrollment, Integer> {
}