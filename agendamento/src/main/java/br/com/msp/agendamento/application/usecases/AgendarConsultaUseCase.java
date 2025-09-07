package br.com.msp.agendamento.application.usecases;

import br.com.msp.agendamento.application.dto.AgendarConsultaInput;
import br.com.msp.agendamento.application.dto.ConsultaOutput;
import br.com.msp.agendamento.application.gateways.ConsultaGateway;
import br.com.msp.agendamento.domain.model.Consulta;

public class AgendarConsultaUseCase {

    private final ConsultaGateway consultaGateway;

    public AgendarConsultaUseCase(ConsultaGateway consultaGateway) {
        this.consultaGateway = consultaGateway;
    }

    public ConsultaOutput executar(AgendarConsultaInput input) {
        Consulta novaConsulta = new Consulta();
        novaConsulta.setPacienteId(input.pacienteId());
        novaConsulta.setMedicoId(input.medicoId());
        novaConsulta.setDataHora(input.dataHora());
        novaConsulta.setEspecialidade(input.especialidade());
        novaConsulta.setCancelada(false);

        Consulta consultaAgendada = consultaGateway.agendar(novaConsulta);

        // TODO: Enviar o evento para a fila de Notificacao

        return new ConsultaOutput(
                consultaAgendada.getId(),
                consultaAgendada.getPacienteId(),
                consultaAgendada.getMedicoId(),
                consultaAgendada.getDataHora(),
                consultaAgendada.getEspecialidade()
        );
    }
}
