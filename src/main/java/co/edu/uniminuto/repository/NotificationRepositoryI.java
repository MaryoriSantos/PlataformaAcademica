package co.edu.uniminuto.repository;

import java.util.List;
import co.edu.uniminuto.entity.Notification;

// Define las operaciones de acceso a datos para notifications.
public interface NotificationRepositoryI {

    // Registra un nuevo registro.
    Notification insertNotification(Notification notification);

    // Actualiza un registro existente.
    Notification updateNotification(Notification notification);

    // Elimina el registro o guarda su estado de baja, segun la entidad.
    int deleteNotification(Notification notification);

    // Busca un registro por su identificador.
    Notification findByNotification(int notificationId);

    // Consulta todos los registros.
    List<Notification> findAll();
}
