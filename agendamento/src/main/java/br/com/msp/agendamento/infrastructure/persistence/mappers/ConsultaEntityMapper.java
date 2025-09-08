package br.com.msp.agendamento.infrastructure.persistence.mappers;

import br.com.msp.agendamento.domain.model.Consulta;
import br.com.msp.agendamento.infrastructure.persistence.entity.ConsultaEntity;
import org.springframework.stereotype.Component;

@Component
public class ConsultaEntityMapper {

    public ConsultaEntity toEntity(Consulta consulta) {
        ConsultaEntity entity = new ConsultaEntity();
        entity.setId(consulta.getId());
        entity.setPacienteId(consulta.getPacienteId());
        entity.setMedicoId(consulta.getMedicoId());
        entity.setDataHora(consulta.getDataHora());
        entity.setEspecialidade(consulta.getEspecialidade());
        entity.setCancelada(consulta.isCancelada());
        return entity;
    }

    public Consulta toDomain(ConsultaEntity entity) {
        Consulta consulta = new Consulta();
        consulta.setId(entity.getId());
        consulta.setPacienteId(entity.getPacienteId());
        consulta.setMedicoId(entity.getMedicoId());
        consulta.setDataHora(entity.getDataHora());
        consulta.setEspecialidade(entity.getEspecialidade());
        consulta.setCancelada(entity.isCancelada());
        return consulta;
    }
}
