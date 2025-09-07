package br.com.msp.agendamento.infrastructure.configuration;

import br.com.msp.agendamento.application.gateways.ConsultaGateway;
import br.com.msp.agendamento.application.usecases.AgendarConsultaUseCase;
import br.com.msp.agendamento.application.usecases.CancelarConsultaUseCase;
import br.com.msp.agendamento.application.usecases.ListarConsultasPorPacienteUseCase;
import br.com.msp.agendamento.application.usecases.ReagendarConsultaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    public AgendarConsultaUseCase agendarConsultaUseCase(ConsultaGateway consultaGateway) {
        return new AgendarConsultaUseCase(consultaGateway);
    }

    @Bean
    public ReagendarConsultaUseCase reagendarConsultaUseCase(ConsultaGateway consultaGateway) {
        return new ReagendarConsultaUseCase(consultaGateway);
    }

    @Bean
    public CancelarConsultaUseCase cancelarConsultaUseCase(ConsultaGateway consultaGateway) {
        return new CancelarConsultaUseCase(consultaGateway);
    }

    @Bean
    public ListarConsultasPorPacienteUseCase listarConsultasPorPacienteUseCase(ConsultaGateway consultaGateway) {
        return new ListarConsultasPorPacienteUseCase(consultaGateway);
    }
}
