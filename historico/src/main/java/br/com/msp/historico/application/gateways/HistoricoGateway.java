package br.com.msp.historico.application.gateways;

import br.com.msp.historico.domain.model.Historico;

import java.util.List;

public interface HistoricoGateway {
    List<Historico> buscarPorPacienteId(Long pacienteId);
    List<Historico> buscarPorConsultaAgendada(Long consultaId);
    List<Historico> buscarPorEspecialidade(String especialidadeId);
    List<Historico> buscarPorStatus(String status);
    Historico criarHistorico(Historico historico);
}
