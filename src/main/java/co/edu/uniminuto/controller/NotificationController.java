package co.edu.uniminuto.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import co.edu.uniminuto.entity.Notification;
import co.edu.uniminuto.services.NotificationServiceI;

// Identifica esta clase como controlador REST.
@RestController
// Define la ruta base del modulo.
@RequestMapping("/notifications")
public class NotificationController {

    // Inyecta el servicio que contiene la logica del modulo.
    @Autowired
    NotificationServiceI notificationService;

    // Consulta todos los registros.
    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.findAll();
    }

    // Consulta un registro por su identificador.
    @GetMapping("/{id}")
    public Notification getNotificationById(@PathVariable int id) {
        return notificationService.findByNotification(id);
    }

    // Registra un nuevo registro a partir del JSON recibido.
    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationService.insertNotification(notification);
    }

    // Actualiza un registro existente.
    @PutMapping
    public Notification updateNotification(@RequestBody Notification notification) {
        return notificationService.updateNotification(notification);
    }

    // Elimina o desactiva un registro usando el JSON recibido.
    @DeleteMapping
    public int deleteNotification(@RequestBody Notification notification) {
        return notificationService.deleteNotification(notification);
    }
}
