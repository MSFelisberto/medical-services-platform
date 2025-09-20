package br.com.msp.historico.infrastructure.persistence.gateways;

import br.com.msp.historico.application.gateways.HistoricoGateway;
import br.com.msp.historico.domain.model.Historico;
import br.com.msp.historico.infrastructure.controllers.mappers.HistoricoMapper;
import br.com.msp.historico.infrastructure.persistence.HistoricoEntity;
import br.com.msp.historico.infrastructure.repository.HistoricoJPARepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HistoricoGatewayImpl implements HistoricoGateway {

    private final HistoricoJPARepository repository;

    public HistoricoGatewayImpl(HistoricoJPARepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Historico> buscarPorPacienteId(Long pacienteId) {
        return repository.findAllByPacienteId(pacienteId).stream()
                .map(HistoricoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Historico criarHistorico(Historico historico) {
        HistoricoEntity entity = HistoricoMapper.toModel(historico);
        HistoricoEntity savedEntity = repository.save(entity);
        return HistoricoMapper.toDomain(savedEntity);
    }
}
