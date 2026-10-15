package co.edu.uniminuto.services;

import java.time.Instant;

import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import co.edu.uniminuto.entity.User;

// Servicio encargado de generar los tokens JWT
@Service
public class JwtService {

    // Permite firmar los tokens utilizando la clave configurada
    private final JwtEncoder jwtEncoder;

    // Constructor para recibir el componente JwtEncoder de Spring
    public JwtService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    // Genera un token para un usuario autenticado
    public String generateToken(User user) {

        // Obtiene la hora actual para establecer la vigencia
        Instant now = Instant.now();

        // Define la informacion que llevara el token
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("PlataformaAcademica")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(3600))
                .subject(user.getEmail())
                .claim("userId", user.getUserId())
                .claim("role", user.getRole() == null
                        ? "USER"
                        : user.getRole().getName())
                .build();

        // Define el algoritmo utilizado para firmar el token
        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();

        // Firma el token y devuelve su contenido
        return jwtEncoder.encode(
                JwtEncoderParameters.from(header, claims))
                .getTokenValue();
    }
}