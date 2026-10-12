package co.edu.uniminuto.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import co.edu.uniminuto.entity.Notification;
import co.edu.uniminuto.jpa.JpaNotification;

// Identifica esta clase como componente de acceso a datos.
@Repository
public class NotificationRepository implements NotificationRepositoryI {

    // Inyecta JPA para acceder a la tabla correspondiente.
    @Autowired
    JpaNotification jpaNotification;

    @Override
    public Notification insertNotification(Notification notification) {
        // Guarda un registro nuevo.
        return jpaNotification.save(notification);
    }

    @Override
    public Notification updateNotification(Notification notification) {
        // Guarda los cambios del registro.
        return jpaNotification.save(notification);
    }

    @Override
    public int deleteNotification(Notification notification) {
                // Busca el registro antes de eliminarlo fisicamente.
Notification existing = jpaNotification.findById(notification.getNotificationId()).orElse(null);
if (existing != null) {
    jpaNotification.delete(existing);
    return 1;
}
// Retorna cero si el registro no existe.
return 0;
    }

    @Override
    public Notification findByNotification(int notificationId) {
        // Busca por identificador y retorna null si no existe.
        return jpaNotification.findById(notificationId).orElse(null);
    }

    @Override
    public List<Notification> findAll() {
        // Retorna todos los registros de la tabla.
        return jpaNotification.findAll();
    }
}
