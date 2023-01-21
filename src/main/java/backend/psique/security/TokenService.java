package backend.psique.security;

import backend.psique.model.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    public String gerarToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API PSIQUE")
                    .withSubject(usuario.getUsername())
                    .withClaim("cpf", usuario.getPsicologo().getCpf())
                    .withClaim("USER_ROLE", usuario.getRoles().get(0).getAuthority())
                    .withExpiresAt(dataExpirar())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Não foi possível gerar o token");
        }
    }
        public String getSubject(String token) {
            try {
                var algoritmo = Algorithm.HMAC256(secret);
                return JWT.require(algoritmo)
                        .withIssuer("API PSIQUE")
                        .build()
                        .verify(token)
                        .getSubject();
            } catch (JWTVerificationException exception) {
                throw new RuntimeException("Token JWT inválido ou expirado!");
            }
        }



    private Instant dataExpirar() {
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
    }
}
