package br.com.msp.autenticacao.infrastructure.security.adapters;

import br.com.msp.autenticacao.application.ports.outbound.TokenService;
import br.com.msp.autenticacao.domain.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Component
public class TokenServiceImpl implements TokenService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpirationInMs;

    private SecretKey key;

    @Override
    public String generateToken(Usuario usuario) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + jwtExpirationInMs);

        if (this.key == null) {
            this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        }

        String roleStr = "ROLE_" + usuario.getPerfil().name();
        List<String> roles = List.of(roleStr);

        return Jwts.builder()
                .setSubject(usuario.getEmail().getValue())
                .claim("userId", usuario.getId().getValue())
                .claim("roles", roles)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            getSecretKey();
            Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error("Validação do JWT falhou: ", e);
            return false;
        }
    }

    @Override
    public String getEmailFromToken(String token) {
        getSecretKey();
        return getClaims(token).getSubject();
    }

    @Override
    public Long getUserIdFromToken(String token) {
        getSecretKey();
        return getClaims(token).get("userId", Long.class);
    }

    @SuppressWarnings("unchecked")
    public List<String> getRolesFromToken(String token) {
        getSecretKey();
        return getClaims(token).get("roles", List.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private void getSecretKey() {
        if (key == null) {
            key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        }
    }
}