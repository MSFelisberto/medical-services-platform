package br.com.msp.historico.infrastructure.adapters;

import br.com.msp.historico.application.dto.CriarHistoricoCommand;
import br.com.msp.historico.application.dto.HistoricoDTO;
import br.com.msp.historico.application.gateways.HistoricoGateway;
import br.com.msp.historico.application.usecase.*;

import java.util.List;

public class HistoricoUseCaseImpl implements HistoricoUseCase {

    private final CriarHistoricoUseCase criarHistoricoUseCase;
    private final BuscarPorPacienteIdUseCase buscarPorPacienteIdUseCase;
    private final BuscarPorIdConsultaAgendadaUseCase buscarPorIdConsultaAgendadaUseCase;
    private final BuscarPorStatusUseCase buscarPorStatus;
    private final BuscarPorEspecialidadeUseCase buscarPorEspecialidade;

    public HistoricoUseCaseImpl(HistoricoGateway historicoGateway) {
        this.criarHistoricoUseCase = new CriarHistoricoUseCase(historicoGateway);
        this.buscarPorPacienteIdUseCase = new BuscarPorPacienteIdUseCase(historicoGateway);
        this.buscarPorIdConsultaAgendadaUseCase =  new BuscarPorIdConsultaAgendadaUseCase(historicoGateway);
        this.buscarPorStatus = new BuscarPorStatusUseCase(historicoGateway);
        this.buscarPorEspecialidade = new BuscarPorEspecialidadeUseCase(historicoGateway);
    }

    @Override
    public void criar(CriarHistoricoCommand command) {
        criarHistoricoUseCase.executar(command);
    }

    @Override
    public List<HistoricoDTO> buscarPorPacienteId(Long pacienteId) {
        return buscarPorPacienteIdUseCase.executar(pacienteId);
    }

    @Override
    public List<HistoricoDTO> buscarPorIdConsultaAgendada(Long idConsultaAgendada) {
        return buscarPorIdConsultaAgendadaUseCase.executar(idConsultaAgendada);
    }

    @Override
    public List<HistoricoDTO> buscarPorStatus(String status) {
        return buscarPorStatus.executar(status);
    }

    @Override
    public List<HistoricoDTO> buscarPorEspecialidade(String especialidade) {
        return buscarPorEspecialidade.executar(especialidade);
    }
}