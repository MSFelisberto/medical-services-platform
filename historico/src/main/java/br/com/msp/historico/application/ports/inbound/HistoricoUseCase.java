package br.com.msp.historico.application.ports.inbound;

import br.com.msp.historico.application.dto.*;
import java.util.List;

public interface HistoricoUseCase {
    HistoricoOutput registrarHistorico(RegistrarHistoricoCommand command);
    HistoricoOutput atualizarHistorico(AtualizarHistoricoCommand command);
    void cancelarHistorico(CancelarHistoricoCommand command);
    List<HistoricoOutput> listarHistoricoPorPaciente(ListarHistoricoQuery query);
    HistoricoOutput buscarPorConsultaId(Long consultaId);
}