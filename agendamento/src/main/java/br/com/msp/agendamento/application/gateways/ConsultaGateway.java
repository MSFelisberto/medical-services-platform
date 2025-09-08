package br.com.msp.agendamento.application.gateways;

import br.com.msp.agendamento.domain.model.Consulta;

import java.util.List;
import java.util.Optional;

public interface ConsultaGateway {

    Consulta agendar(Consulta consulta);
    Consulta reagendar(Consulta consulta);
    void cancelar(Consulta consulta);
    Optional<Consulta> buscarPorId(Long id);
    List<Consulta> buscarPorPacienteId(Long pacienteId);
}
