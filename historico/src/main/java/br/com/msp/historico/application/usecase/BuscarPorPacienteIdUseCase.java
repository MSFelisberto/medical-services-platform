package br.com.msp.historico.application.usecase;

import br.com.msp.historico.application.dto.HistoricoDTO;
import br.com.msp.historico.application.gateways.HistoricoGateway;
import br.com.msp.historico.domain.model.Historico;

import java.util.List;
import java.util.stream.Collectors;

public class BuscarPorPacienteIdUseCase {
    private final HistoricoGateway historicoGateway;

    public BuscarPorPacienteIdUseCase(HistoricoGateway historicoGateway) {
        this.historicoGateway = historicoGateway;
    }

    public List<HistoricoDTO> executar(Long pacienteId) {
        List<Historico> dominios = historicoGateway.buscarPorPacienteId(pacienteId);

        return dominios.stream()
                .map(h -> new HistoricoDTO(
                        h.getId(),
                        h.getDataRealizacao(),
                        h.getEspecialidade(),
                        h.getDiagnostico(),
                        h.getPrescricao(),
                        h.getObservacoes()))
                .collect(Collectors.toList());
    }
}
