package co.edu.uniminuto.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniminuto.entity.User;
import co.edu.uniminuto.services.JwtService;
import co.edu.uniminuto.services.UserServiceI;

@RestController
@RequestMapping("user")
public class UserController {

    // Servicio que gestiona las operaciones de los usuarios
    @Autowired
    UserServiceI userService;

    // Servicio que genera los tokens JWT
    @Autowired
    JwtService jwtService;

    // Consulta todos los usuarios
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    // Busca un usuario por su identificador
    @GetMapping("/id")
    public User getByUser(@RequestParam int id) {
        return userService.findByUser(id);
    }

    // Busca un usuario por su correo
    @GetMapping("/email")
    public User getByEmail(@RequestParam String email) {
        return userService.findByEmail(email);
    }

    // Registra un usuario nuevo
    @PostMapping
    public User postUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

    // Actualiza un usuario existente
    @PutMapping
    public User putUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    // Valida las credenciales y genera un token JWT
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @RequestParam String email,
            @RequestParam String password) {

        // Comprueba el correo y la contrasena mediante el servicio
        User user = userService.login(email, password);

        // Rechaza las credenciales incorrectas
        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                            "error", "Correo o contrasena incorrectos"));
        }

        // Genera un token para el usuario autenticado
        String token = jwtService.generateToken(user);

        // Devuelve el token para utilizarlo en las siguientes solicitudes
        return ResponseEntity.ok(Map.of(
                "token", token,
                "tokenType", "Bearer",
                "email", user.getEmail()
        ));
    }
}