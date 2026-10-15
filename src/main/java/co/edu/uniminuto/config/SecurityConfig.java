
package co.edu.uniminuto.config;

import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Configura los componentes de seguridad de la aplicacion
@Configuration
public class SecurityConfig {

	// Proporciona el mecanismo para proteger contrasenas con BCrypt
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Convierte la clave secreta Base64 en una clave para firmar JWT
	@Bean
	public SecretKey jwtSecretKey(@Value("${JWT_SECRET}") String jwtSecret) {

		byte[] keyBytes;

		try {
			// Decodifica la clave generada previamente en PowerShell
			keyBytes = Base64.getDecoder().decode(jwtSecret);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("JWT_SECRET debe contener una clave Base64 valida", e);
		}

		// Comprueba que la clave tenga al menos 256 bits
		if (keyBytes.length < 32) {
			throw new IllegalArgumentException("JWT_SECRET debe contener al menos 32 bytes");
		}

		// Crea la clave que utilizara el algoritmo HMAC SHA-256
		return new SecretKeySpec(keyBytes, "HmacSHA256");
	}

	// Permite generar tokens JWT firmados
	@Bean
	public JwtEncoder jwtEncoder(SecretKey jwtSecretKey) {
		return NimbusJwtEncoder.withSecretKey(jwtSecretKey).algorithm(MacAlgorithm.HS256).build();
	}

	// Permite comprobar la firma y validez de los tokens JWT
	@Bean
	public JwtDecoder jwtDecoder(SecretKey jwtSecretKey) {
		return NimbusJwtDecoder.withSecretKey(jwtSecretKey).macAlgorithm(MacAlgorithm.HS256).build();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		// Desactiva CSRF porque la API utiliza tokens y no sesiones web.
		http.csrf(csrf -> csrf.disable());

		// Evita mantener sesiones de autenticacion en el servidor.
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		// Define cuales rutas son publicas y cuales necesitan autenticacion.
		http.authorizeHttpRequests(auth -> auth

				// Permite cargar las paginas y sus archivos CSS y JavaScript.
				.requestMatchers("/", "/index.html", "/registro.html", "/dashboard.html", "/css/**", "/js/**",
						"/favicon.ico", "/error")
				.permitAll()

				// Permite registrar usuarios e iniciar sesion.
				.requestMatchers(HttpMethod.POST, "/user", "/user/login").permitAll()

				// Las demas rutas requieren autenticacion.
				.anyRequest().authenticated());

		// Valida los tokens JWT enviados en el encabezado Authorization.
		http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

		return http.build();
	}

}
