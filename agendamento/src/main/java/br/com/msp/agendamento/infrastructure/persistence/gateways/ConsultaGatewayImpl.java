package br.com.msp.agendamento.infrastructure.persistence.gateways;

import br.com.msp.agendamento.application.gateways.ConsultaGateway;
import br.com.msp.agendamento.domain.model.Consulta;
import br.com.msp.agendamento.infrastructure.persistence.entity.ConsultaEntity;
import br.com.msp.agendamento.infrastructure.persistence.mappers.ConsultaEntityMapper;
import br.com.msp.agendamento.infrastructure.persistence.repository.ConsultaJPARepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ConsultaGatewayImpl implements ConsultaGateway {

    private final ConsultaJPARepository repository;
    private final ConsultaEntityMapper mapper;

    public ConsultaGatewayImpl(ConsultaJPARepository repository, ConsultaEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Consulta agendar(Consulta consulta) {
        ConsultaEntity entity = mapper.toEntity(consulta);
        ConsultaEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Consulta reagendar(Consulta consulta) {
        ConsultaEntity entity = mapper.toEntity(consulta);
        ConsultaEntity updatedEntity = repository.save(entity);
        return mapper.toDomain(updatedEntity);
    }

    @Override
    public void cancelar(Consulta consulta) {
        ConsultaEntity entity = mapper.toEntity(consulta);
        repository.save(entity);
    }

    @Override
    public Optional<Consulta> buscarPorId(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Consulta> buscarPorPacienteId(Long pacienteId) {
        return repository.findByPacienteId(pacienteId)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
