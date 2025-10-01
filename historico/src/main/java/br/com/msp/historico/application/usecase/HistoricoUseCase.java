package br.com.msp.historico.application.usecase;

import br.com.msp.historico.application.dto.CriarHistoricoCommand;
import br.com.msp.historico.application.dto.HistoricoDTO;

import java.util.List;

public interface HistoricoUseCase {
    void criar(CriarHistoricoCommand command);
    List<HistoricoDTO> buscarPorPacienteId(Long pacienteId);
    List<HistoricoDTO> buscarPorIdConsultaAgendada(Long idConsultaAgendada);
    List<HistoricoDTO> buscarPorStatus(String status);
    List<HistoricoDTO> buscarPorEspecialidade(String especialidade);
}
