package co.edu.uniminuto.services;

import java.util.List;
import co.edu.uniminuto.entity.Notification;

// Define las operaciones de negocio disponibles para notifications.
public interface NotificationServiceI {

    // Registra un nuevo registro.
    Notification insertNotification(Notification notification);

    // Actualiza un registro existente.
    Notification updateNotification(Notification notification);

    // Elimina o desactiva un registro.
    int deleteNotification(Notification notification);

    // Busca un registro por identificador.
    Notification findByNotification(int notificationId);

    // Consulta todos los registros.
    List<Notification> findAll();
}
