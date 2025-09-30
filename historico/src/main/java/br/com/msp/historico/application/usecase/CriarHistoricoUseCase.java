package br.com.msp.historico.application.usecase;

import br.com.msp.historico.application.dto.CriarHistoricoCommand;
import br.com.msp.historico.application.gateways.HistoricoGateway;
import br.com.msp.historico.domain.model.Historico;

public class CriarHistoricoUseCase {
    private final HistoricoGateway historicoGateway;

    public CriarHistoricoUseCase(HistoricoGateway historicoGateway) {
        this.historicoGateway = historicoGateway;
    }

    public void executar(CriarHistoricoCommand command) {
        Historico historico = new Historico();
        historico.setIdConsultaAgendada(command.idConsultaAgendada());
        historico.setDataRealizacao(command.dataRealizacao());
        historico.setEspecialidade(command.especialidade());
        historico.setPacienteId(command.pacienteId());
        historico.setMedicoId(command.medicoId());

        historicoGateway.criarHistorico(historico);
    }
}
