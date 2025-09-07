package br.com.msp.agendamento.application.usecases;

import br.com.msp.agendamento.application.dto.ConsultaOutput;
import br.com.msp.agendamento.application.gateways.ConsultaGateway;
import br.com.msp.agendamento.domain.model.Consulta;

import java.util.List;
import java.util.stream.Collectors;

public class ListarConsultasPorPacienteUseCase {

    private final ConsultaGateway consultaGateway;

    public ListarConsultasPorPacienteUseCase(ConsultaGateway consultaGateway) {
        this.consultaGateway = consultaGateway;
    }

    public List<ConsultaOutput> executar(Long pacienteId) {
        return consultaGateway.buscarPorPacienteId(pacienteId).stream()
                .map(consulta -> new ConsultaOutput(
                        consulta.getId(),
                        consulta.getPacienteId(),
                        consulta.getMedicoId(),
                        consulta.getDataHora(),
                        consulta.getEspecialidade()
                ))
                .collect(Collectors.toList());
    }
}
