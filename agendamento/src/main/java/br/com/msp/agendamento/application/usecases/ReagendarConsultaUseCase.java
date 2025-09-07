package br.com.msp.agendamento.application.usecases;

import br.com.msp.agendamento.application.dto.ConsultaOutput;
import br.com.msp.agendamento.application.dto.ReagendarConsultaInput;
import br.com.msp.agendamento.application.gateways.ConsultaGateway;
import br.com.msp.agendamento.domain.model.Consulta;
import jakarta.persistence.EntityNotFoundException;

public class ReagendarConsultaUseCase {

    private final ConsultaGateway consultaGateway;

    public ReagendarConsultaUseCase(ConsultaGateway consultaGateway) {
        this.consultaGateway = consultaGateway;
    }

    public ConsultaOutput executar(ReagendarConsultaInput input) {
        Consulta consulta = consultaGateway.buscarPorId(input.consultaId())
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada com o ID: " + input.consultaId()));

        consulta.setPacienteId(input.pacienteId());
        consulta.setMedicoId(input.medicoId());
        consulta.setDataHora(input.dataHora());
        consulta.setEspecialidade(input.especialidade());

        Consulta consultaReagendada = consultaGateway.reagendar(consulta);

        //TODO: Enviar evento de reagendamento para a fila de Notificações

        return new ConsultaOutput(
                consultaReagendada.getId(),
                consultaReagendada.getPacienteId(),
                consultaReagendada.getMedicoId(),
                consultaReagendada.getDataHora(),
                consultaReagendada.getEspecialidade()
        );
    }
}
