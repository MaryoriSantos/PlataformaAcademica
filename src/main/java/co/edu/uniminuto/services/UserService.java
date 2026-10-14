package co.edu.uniminuto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.uniminuto.entity.User;
import co.edu.uniminuto.repository.UserRepository;

@Service
public class UserService implements UserServiceI {

	@Autowired
	UserRepository userRepository;

	// Permite codificar y comprobar las contrasenas con BCrypt
	@Autowired
	PasswordEncoder passwordEncoder;

	// Registra un usuario y protege su contrasena con BCrypt
	@Override
	public User insertUser(User user) {

		// Comprueba que el correo no este registrado
		if (userRepository.findByEmail(user.getEmail()) == null) {

			// Codifica la contrasena antes de guardarla en MySQL
			user.setPassword(passwordEncoder.encode(user.getPassword()));

			// Guarda el usuario con la contrasena protegida
			return userRepository.insertUser(user);
		}

		// Retorna null si el correo ya existe
		return null;
	}

	@Override
	public User updateUser(User user) {

		if (userRepository.findByUser(user.getUserId()) != null) {
			return userRepository.updateUser(user);
		}

		return null;
	}

	@Override
	public int deleteUser(User user) {

		if (userRepository.findByUser(user.getUserId()) != null) {
			user.setStatus((byte) 0);
			userRepository.updateUser(user);
			return 1;
		}

		return 0;
	}

	@Override
	public User findByUser(int userId) {
		return userRepository.findByUser(userId);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	// Valida las credenciales del usuario para iniciar sesion
	@Override
	public User login(String email, String password) {

		// Busca el usuario por su correo electronico
		User user = userRepository.findByEmail(email);

		// Verifica que el usuario exista y tenga contrasena
		if (user == null || user.getPassword() == null) {
			return null;
		}

		// Obtiene la contrasena almacenada en la base de datos
		String storedPassword = user.getPassword();

		// Comprueba las contrasenas que ya estan codificadas con BCrypt
		if (storedPassword.startsWith("$2") && passwordEncoder.matches(password, storedPassword)) {
			return user;
		}

		// Permite migrar una contrasena antigua que estaba en texto normal
		if (!storedPassword.startsWith("$2") && storedPassword.equals(password)) {

			// Codifica la contrasena antigua
			user.setPassword(passwordEncoder.encode(password));

			// Guarda el cambio en MySQL
			userRepository.updateUser(user);

			return user;
		}

		// Rechaza las credenciales incorrectas
		return null;
	}
}
