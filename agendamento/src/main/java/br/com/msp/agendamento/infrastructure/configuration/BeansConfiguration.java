package br.com.msp.agendamento.infrastructure.configuration;

import br.com.msp.agendamento.application.gateways.ConsultaGateway;
import br.com.msp.agendamento.application.usecases.*;
import br.com.msp.agendamento.infrastructure.adapters.inbound.AgendamentoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    public AgendamentoUseCase agendamentoUseCase(ConsultaGateway consultaGateway) {
        return new AgendamentoUseCaseImpl(consultaGateway);
    }
}
