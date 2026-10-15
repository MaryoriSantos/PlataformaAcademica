
package co.edu.uniminuto.config;

import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
            throw new IllegalArgumentException(
                    "JWT_SECRET debe contener una clave Base64 valida", e);
        }

        // Comprueba que la clave tenga al menos 256 bits
        if (keyBytes.length < 32) {
            throw new IllegalArgumentException(
                    "JWT_SECRET debe contener al menos 32 bytes");
        }

        // Crea la clave que utilizara el algoritmo HMAC SHA-256
        return new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    // Permite generar tokens JWT firmados
    @Bean
    public JwtEncoder jwtEncoder(SecretKey jwtSecretKey) {
        return NimbusJwtEncoder.withSecretKey(jwtSecretKey)
                .algorithm(MacAlgorithm.HS256)
                .build();
    }

    // Permite comprobar la firma y validez de los tokens JWT
    @Bean
    public JwtDecoder jwtDecoder(SecretKey jwtSecretKey) {
        return NimbusJwtDecoder.withSecretKey(jwtSecretKey)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
    }

    // Mantiene las rutas accesibles mientras integramos el login con JWT
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        // Deshabilita CSRF para la API REST actual
        http.csrf(csrf -> csrf.disable());

        // Temporalmente no exige token en las rutas
        // La proteccion se configurara despues de integrar login y frontend
        http.authorizeHttpRequests(auth ->
                auth.anyRequest().permitAll());

        return http.build();
    }
}
