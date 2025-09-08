package br.com.msp.agendamento.infrastructure.persistence.repository;

import br.com.msp.agendamento.infrastructure.persistence.entity.ConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultaJPARepository extends JpaRepository<ConsultaEntity, Long> {
    List<ConsultaEntity> findByPacienteId(Long pacienteId);
}
