package br.com.msp.autenticacao.infrastructure.configuration;

import br.com.msp.autenticacao.application.ports.inbound.*;
import br.com.msp.autenticacao.application.ports.outbound.*;
import br.com.msp.autenticacao.application.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    public PacienteUseCase pacienteUseCase(
            PacienteRepository pacienteRepository,
            PasswordEncoder passwordEncoder) {
        return new PacienteUseCaseImpl(pacienteRepository, passwordEncoder);
    }

    @Bean
    public FuncionarioUseCase funcionarioUseCase(
            FuncionarioRepository funcionarioRepository,
            PasswordEncoder passwordEncoder) {
        return new FuncionarioUseCaseImpl(funcionarioRepository, passwordEncoder);
    }

    @Bean
    public AutenticacaoUseCase autenticacaoUseCase(
            PacienteRepository pacienteRepository,
            FuncionarioRepository funcionarioRepository,
            PasswordEncoder passwordEncoder,
            TokenService tokenService) {
        return new AutenticacaoUseCaseImpl(
                pacienteRepository, funcionarioRepository, passwordEncoder, tokenService);
    }
}
