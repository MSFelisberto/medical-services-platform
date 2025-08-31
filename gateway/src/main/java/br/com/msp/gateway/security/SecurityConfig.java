package br.com.msp.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange
                        .anyExchange().permitAll())
                .build();
    }

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder, AuthenticationFilter authenticationFilter) {
        return builder.routes()
                .route("autenticacao-ms", r -> r.path("/autenticacao/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://autenticacao"))
                .route("agendamento-ms", r -> r.path("/agendamento/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://agendamento"))
                .route("historico-ms", r -> r.path("/historico/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://historico"))
                .route("notificacoes-ms", r -> r.path("/notificacoes/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://notificacoes"))
                .build();
    }
}
