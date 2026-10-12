package co.edu.uniminuto.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uniminuto.entity.Notification;
import co.edu.uniminuto.repository.NotificationRepository;

// Identifica esta clase como componente de la capa de servicio.
@Service
public class NotificationService implements NotificationServiceI {

    // Inyecta el repositorio para acceder a los datos.
    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public Notification insertNotification(Notification notification) {
        // Delega el registro al repositorio.
        return notificationRepository.insertNotification(notification);
    }

    @Override
    public Notification updateNotification(Notification notification) {
        // Actualiza solamente si el registro ya existe.
        if (notificationRepository.findByNotification(notification.getNotificationId()) != null) {
            return notificationRepository.updateNotification(notification);
        }
        // Retorna null si no existe.
        return null;
    }

    @Override
    public int deleteNotification(Notification notification) {
                // Comprueba que el registro exista antes de eliminarlo.
if (notificationRepository.findByNotification(notification.getNotificationId()) != null) {
    return notificationRepository.deleteNotification(notification);
}
// Retorna cero si no existe.
return 0;
    }

    @Override
    public Notification findByNotification(int notificationId) {
        // Delega la busqueda al repositorio.
        return notificationRepository.findByNotification(notificationId);
    }

    @Override
    public List<Notification> findAll() {
        // Delega la consulta al repositorio.
        return notificationRepository.findAll();
    }
}
