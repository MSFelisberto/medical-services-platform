package br.com.msp.autenticacao.config;

import br.com.msp.autenticacao.application.gateways.UsuarioGateway;
import br.com.msp.autenticacao.application.usecases.CadastrarUsuarioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    public CadastrarUsuarioUseCase cadastrarUsuarioUseCase(UsuarioGateway usuarioGateway) {
        return new CadastrarUsuarioUseCase(usuarioGateway);
    }
}
