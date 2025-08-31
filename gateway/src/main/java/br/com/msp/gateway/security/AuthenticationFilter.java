package br.com.msp.gateway.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.util.List;

@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {

    private final List<String> publicRoutes = List.of(
            "/autenticacao/auth/login",
            "/autenticacao/usuarios"
    );

    @Value("${jwt.secret}")
    private String jwtSecret;

    private SecretKey key;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (isPublicRoute(request)) {
            return chain.filter(exchange);
        }

        if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            return onError(exchange, HttpStatus.UNAUTHORIZED, "Header de autorização ausente");
        }

        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return onError(exchange, HttpStatus.UNAUTHORIZED, "Header de autorização mal formatado");
        }
        String token = authHeader.substring(7);

        try {
            if (this.key == null) {
                this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
            }
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        } catch (Exception e) {
            return onError(exchange, HttpStatus.UNAUTHORIZED, "Token JWT inválido ou expirado");
        }

        return chain.filter(exchange);
    }

    private boolean isPublicRoute(ServerHttpRequest request) {
        return publicRoutes.stream().anyMatch(uri -> request.getURI().getPath().equals(uri));
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus status, String message) {
        exchange.getResponse().setStatusCode(status);
        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse()
                .bufferFactory().wrap(message.getBytes())));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}