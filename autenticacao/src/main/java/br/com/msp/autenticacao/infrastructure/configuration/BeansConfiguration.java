package br.com.msp.autenticacao.infrastructure.configuration;

import br.com.msp.autenticacao.application.ports.inbound.AutenticacaoUseCase;
import br.com.msp.autenticacao.application.ports.inbound.UsuarioUseCase;
import br.com.msp.autenticacao.application.ports.outbound.PasswordEncoder;
import br.com.msp.autenticacao.application.ports.outbound.TokenService;
import br.com.msp.autenticacao.application.ports.outbound.UsuarioRepository;
import br.com.msp.autenticacao.application.services.AutenticacaoUseCaseImpl;
import br.com.msp.autenticacao.application.services.UsuarioUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    public UsuarioUseCase usuarioUseCase(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {
        return new UsuarioUseCaseImpl(usuarioRepository, passwordEncoder);
    }

    @Bean
    public AutenticacaoUseCase autenticacaoUseCase(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            TokenService tokenService) {
        return new AutenticacaoUseCaseImpl(usuarioRepository, passwordEncoder, tokenService);
    }
}