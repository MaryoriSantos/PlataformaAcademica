package co.edu.uniminuto.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.uniminuto.entity.Notification;

// Proporciona las operaciones CRUD para la entidad Notification.
public interface JpaNotification extends JpaRepository<Notification, Integer> {

}
