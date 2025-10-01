package br.com.msp.historico.infrastructure.repository;

import br.com.msp.historico.infrastructure.persistence.HistoricoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricoJPARepository extends JpaRepository<HistoricoEntity, Long> {
    List<HistoricoEntity> findAllByPacienteId(Long id);
    List<HistoricoEntity> findAllByIdConsultaAgendada(Long id);
    List<HistoricoEntity> findAllByEspecialidade(String especialidade);
    List<HistoricoEntity> findAllByStatus(String status);
}
