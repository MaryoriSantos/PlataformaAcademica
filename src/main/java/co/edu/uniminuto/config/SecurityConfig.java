
package co.edu.uniminuto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

// Configura los componentes de seguridad de la aplicacion
@Configuration
public class SecurityConfig {

    // Proporciona el mecanismo para proteger las contrasenas con BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Mantiene las rutas accesibles mientras integramos la autenticacion
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        // Deshabilita CSRF para el desarrollo actual de la API REST
        http.csrf(csrf -> csrf.disable());

        // Permite las solicitudes durante esta etapa de configuracion
        // Posteriormente protegeremos las rutas con JWT
        http.authorizeHttpRequests(auth ->
                auth.anyRequest().permitAll());

        return http.build();
    }
}
