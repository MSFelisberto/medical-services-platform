package br.com.msp.historico.infrastructure.configuration;

import br.com.msp.historico.application.ports.inbound.HistoricoUseCase;
import br.com.msp.historico.application.ports.outbound.HistoricoRepository;
import br.com.msp.historico.application.services.HistoricoUseCaseImpl;
import br.com.msp.historico.domain.services.HistoricoAuthorizationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    public HistoricoAuthorizationService historicoAuthorizationService() {
        return new HistoricoAuthorizationService();
    }

    @Bean
    public HistoricoUseCase historicoUseCase(
            HistoricoRepository historicoRepository,
            HistoricoAuthorizationService authorizationService) {
        return new HistoricoUseCaseImpl(historicoRepository, authorizationService);
    }
}