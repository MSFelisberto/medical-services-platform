package br.com.msp.agendamento.infrastructure.adapters.inbound;

import br.com.msp.agendamento.application.dto.AgendarConsultaInput;
import br.com.msp.agendamento.application.dto.ConsultaOutput;
import br.com.msp.agendamento.application.dto.ReagendarConsultaInput;
import br.com.msp.agendamento.application.gateways.ConsultaGateway;
import br.com.msp.agendamento.application.usecases.*;
import org.springframework.security.core.parameters.P;

import java.util.List;

public class AgendamentoUseCaseImpl implements AgendamentoUseCase {
    private final AgendarConsultaUseCase agendarConsultaUseCase;
    private final ReagendarConsultaUseCase reagendarConsultaUseCase;
    private final CancelarConsultaUseCase cancelarConsultaUseCase;
    private final ListarConsultasPorPacienteUseCase listarConsultasPorPacienteUseCase;

    public AgendamentoUseCaseImpl(ConsultaGateway consultaGateway) {
        this.agendarConsultaUseCase = new AgendarConsultaUseCase(consultaGateway);
        this.reagendarConsultaUseCase = new ReagendarConsultaUseCase(consultaGateway);
        this.cancelarConsultaUseCase = new CancelarConsultaUseCase(consultaGateway);
        this.listarConsultasPorPacienteUseCase = new ListarConsultasPorPacienteUseCase(consultaGateway);
    }

    @Override
    public ConsultaOutput agendarConsulta(AgendarConsultaInput input) {
        return agendarConsultaUseCase.executar(input);
    }

    @Override
    public ConsultaOutput reagendarConsulta(ReagendarConsultaInput input) {
        return reagendarConsultaUseCase.executar(input);
    }

    @Override
    public void cancelarConsulta(Long consultaId) {
        cancelarConsultaUseCase.executar(consultaId);
    }

    @Override
    public List<ConsultaOutput> listarConsultasPorPaciente(Long pacienteId) {
        return listarConsultasPorPacienteUseCase.executar(pacienteId);
    }
}
