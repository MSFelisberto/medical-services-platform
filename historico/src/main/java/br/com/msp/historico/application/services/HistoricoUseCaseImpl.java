package br.com.msp.historico.application.services;

import br.com.msp.historico.application.dto.*;
import br.com.msp.historico.application.ports.inbound.HistoricoUseCase;
import br.com.msp.historico.application.ports.outbound.HistoricoRepository;
import br.com.msp.historico.domain.exception.*;
import br.com.msp.historico.domain.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class HistoricoUseCaseImpl implements HistoricoUseCase {

    private final HistoricoRepository historicoRepository;

    public HistoricoUseCaseImpl(HistoricoRepository historicoRepository) {
        this.historicoRepository = historicoRepository;
    }

    @Override
    public HistoricoOutput registrarHistorico(RegistrarHistoricoCommand command) {
        // Verifica se já existe histórico para esta consulta
        if (historicoRepository.existsByConsultaId(new ConsultaId(command.consultaId()))) {
            throw new HistoricoBusinessException(
                    "Já existe histórico para a consulta ID: " + command.consultaId()
            );
        }

        HistoricoConsulta historico = new HistoricoConsulta(
                new ConsultaId(command.consultaId()),
                new PacienteId(command.pacienteId()),
                new MedicoId(command.medicoId()),
                command.dataHora(),
                command.especialidade()
        );

        HistoricoConsulta historicoSalvo = historicoRepository.save(historico);
        return mapToOutput(historicoSalvo);
    }

    @Override
    public HistoricoOutput atualizarHistorico(AtualizarHistoricoCommand command) {
        ConsultaId consultaId = new ConsultaId(command.consultaId());

        HistoricoConsulta historico = historicoRepository.findByConsultaId(consultaId)
                .orElseThrow(() -> new HistoricoNotFoundException(
                        "Histórico não encontrado para consulta ID: " + command.consultaId()
                ));

        MedicoId novoMedico = new MedicoId(command.novoMedicoId());
        historico.reagendar(command.novaDataHora(), novoMedico, command.novaEspecialidade());

        HistoricoConsulta historicoAtualizado = historicoRepository.save(historico);
        return mapToOutput(historicoAtualizado);
    }

    @Override
    public void cancelarHistorico(CancelarHistoricoCommand command) {
        ConsultaId consultaId = new ConsultaId(command.consultaId());

        HistoricoConsulta historico = historicoRepository.findByConsultaId(consultaId)
                .orElseThrow(() -> new HistoricoNotFoundException(
                        "Histórico não encontrado para consulta ID: " + command.consultaId()
                ));

        historico.cancelar(command.motivoCancelamento());
        historicoRepository.save(historico);
    }

    @Override
    public List<HistoricoOutput> listarHistoricoPorPaciente(ListarHistoricoQuery query) {
        PacienteId pacienteId = new PacienteId(query.pacienteId());

        // Verifica permissões: paciente só pode ver seu próprio histórico
        if (query.currentUser().hasRole("PACIENTE")) {
            PacienteId currentUserId = new PacienteId(query.currentUser().getId());
            if (!pacienteId.equals(currentUserId)) {
                throw new AuthorizationException(
                        "Acesso negado. Paciente só pode visualizar o próprio histórico."
                );
            }
        }

        return historicoRepository.findByPacienteId(pacienteId)
                .stream()
                .map(this::mapToOutput)
                .collect(Collectors.toList());
    }

    @Override
    public HistoricoOutput buscarPorConsultaId(Long consultaId) {
        ConsultaId id = new ConsultaId(consultaId);

        HistoricoConsulta historico = historicoRepository.findByConsultaId(id)
                .orElseThrow(() -> new HistoricoNotFoundException(
                        "Histórico não encontrado para consulta ID: " + consultaId
                ));

        return mapToOutput(historico);
    }

    private HistoricoOutput mapToOutput(HistoricoConsulta historico) {
        return new HistoricoOutput(
                historico.getId().getValue(),
                historico.getConsultaId().getValue(),
                historico.getPacienteId().getValue(),
                historico.getMedicoId().getValue(),
                historico.getDataHora(),
                historico.getEspecialidade(),
                historico.getStatus().name(),
                historico.getObservacoes(),
                historico.getDataCriacao(),
                historico.getDataAtualizacao()
        );
    }
}