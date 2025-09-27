package br.com.msp.historico.application.gateways;

import br.com.msp.historico.domain.model.Historico;

import java.util.List;

public interface HistoricoGateway {
    List<Historico> buscarPorPacienteId(Long pacienteId);
    Historico criarHistorico(Historico historico);
}
