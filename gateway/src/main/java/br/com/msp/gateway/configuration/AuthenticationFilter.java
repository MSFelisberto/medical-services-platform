package br.com.msp.gateway.configuration;

import br.com.msp.gateway.util.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final RouterValidator validator;
    private final JwtUtil jwtUtil;


    public AuthenticationFilter(RouterValidator validator, JwtUtil jwtUtil) {
        super(Config.class);
        this.validator = validator;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    if (jwtUtil.isInvalid(authHeader)) {
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token expired");
                    }

                    populateRequestWithHeaders(exchange, authHeader);

                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized access to application");
                }
            }
            return chain.filter(exchange);
        });
    }

    @SuppressWarnings("unchecked")
    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
        String email = jwtUtil.getEmailFromToken(token);
        List<String> roles = jwtUtil.getRolesFromToken(token);


        exchange.getRequest().mutate()
                .header("X-User-Email", email)
                .header("X-User_Roles", String.join(",", roles))
                .build();
    }


    public static class Config {}
}
