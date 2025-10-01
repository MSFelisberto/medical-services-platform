package br.com.msp.historico.application.usecase;

import br.com.msp.historico.application.dto.HistoricoDTO;
import br.com.msp.historico.application.gateways.HistoricoGateway;
import br.com.msp.historico.domain.model.Historico;

import java.util.List;
import java.util.stream.Collectors;

public class BuscarPorIdConsultaAgendadaUseCase {
    private final HistoricoGateway historicoGateway;

    public BuscarPorIdConsultaAgendadaUseCase(HistoricoGateway historicoGateway) {
        this.historicoGateway = historicoGateway;
    }

    public List<HistoricoDTO> executar(Long idConsultaAgendada) {
        List<Historico> dominios = historicoGateway.buscarPorConsultaAgendada(idConsultaAgendada);

        return dominios.stream()
                .map(h -> new HistoricoDTO(
                        h.getId(),
                        h.getIdConsultaAgendada(),
                        h.getPacienteId(),
                        h.getMedicoId(),
                        h.getDataRealizacao(),
                        h.getEspecialidade(),
                        h.getStatus()))
                .collect(Collectors.toList());
    }
}
