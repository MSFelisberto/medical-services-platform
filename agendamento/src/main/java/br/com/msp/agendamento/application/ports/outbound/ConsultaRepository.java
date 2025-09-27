package br.com.msp.agendamento.application.ports.outbound;

import br.com.msp.agendamento.domain.model.*;
import java.util.List;
import java.util.Optional;

public interface ConsultaRepository {
    Consulta save(Consulta consulta);
    Optional<Consulta> findById(ConsultaId id);
    List<Consulta> findByPacienteId(PacienteId pacienteId);
}