package br.com.msp.historico.application.ports.outbound;

import br.com.msp.historico.domain.model.*;
import java.util.List;
import java.util.Optional;

public interface HistoricoRepository {
    HistoricoConsulta save(HistoricoConsulta historico);
    Optional<HistoricoConsulta> findByConsultaId(ConsultaId consultaId);
    List<HistoricoConsulta> findByPacienteId(PacienteId pacienteId);
    boolean existsByConsultaId(ConsultaId consultaId);
}