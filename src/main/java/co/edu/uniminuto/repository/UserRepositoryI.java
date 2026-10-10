package co.edu.uniminuto.repository;

import java.util.List;

import co.edu.uniminuto.entity.User;

public interface UserRepositoryI {

    User insertUser(User user);

    User updateUser(User user);

    int deleteUser(User user);

    User findByUser(int userId);

    List<User> findAll();

    User findByEmail(String email);
    
    // Permite validar el correo y la contraseña del usuario para iniciar sesion
    User login(String email, String password);
}