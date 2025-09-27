package br.com.msp.historico.infrastructure.config;

import br.com.msp.historico.application.usecase.HistoricoUseCase;
import br.com.msp.historico.infrastructure.adapters.HistoricoUseCaseImpl;
import br.com.msp.historico.infrastructure.persistence.gateways.HistoricoGatewayImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    public HistoricoUseCase historicoUseCase(HistoricoGatewayImpl historicoGateway) {
        return new HistoricoUseCaseImpl(historicoGateway);
    }
}