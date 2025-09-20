package br.com.msp.historico.infrastructure.adapters;

import br.com.msp.historico.application.dto.CriarHistoricoCommand;
import br.com.msp.historico.application.dto.HistoricoDTO;
import br.com.msp.historico.application.gateways.HistoricoGateway;
import br.com.msp.historico.application.usecase.BuscarPorPacienteIdUseCase;
import br.com.msp.historico.application.usecase.CriarHistoricoUseCase;
import br.com.msp.historico.application.usecase.HistoricoUseCase;
import br.com.msp.historico.domain.model.Historico;
import java.util.List;
import java.util.stream.Collectors;

public class HistoricoUseCaseImpl implements HistoricoUseCase {

    private final CriarHistoricoUseCase criarHistoricoUseCase;
    private final BuscarPorPacienteIdUseCase buscarPorPacienteIdUseCase;

    public HistoricoUseCaseImpl(HistoricoGateway historicoGateway) {
        this.criarHistoricoUseCase = new CriarHistoricoUseCase(historicoGateway);
        this.buscarPorPacienteIdUseCase = new BuscarPorPacienteIdUseCase(historicoGateway);
    }

    @Override
    public void criar(CriarHistoricoCommand command) {
        criarHistoricoUseCase.executar(command);
    }

    @Override
    public List<HistoricoDTO> buscarPorPacienteId(Long pacienteId) {
        return buscarPorPacienteIdUseCase.executar(pacienteId);
    }
}