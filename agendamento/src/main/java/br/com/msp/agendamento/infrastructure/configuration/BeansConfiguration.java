package br.com.msp.agendamento.infrastructure.configuration;

import br.com.msp.agendamento.application.ports.inbound.AgendamentoUseCase;
import br.com.msp.agendamento.application.ports.outbound.*;
import br.com.msp.agendamento.application.services.AgendamentoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    public AgendamentoUseCase agendamentoUseCase(
            ConsultaRepository consultaRepository,
            PacienteService pacienteService,
            NotificationService notificationService) {

        return new AgendamentoUseCaseImpl(
                consultaRepository,
                pacienteService,
                notificationService
        );
    }
}