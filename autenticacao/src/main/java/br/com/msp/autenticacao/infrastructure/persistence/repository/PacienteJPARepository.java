package br.com.msp.autenticacao.infrastructure.persistence.repository;

import br.com.msp.autenticacao.infrastructure.persistence.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PacienteJPARepository extends JpaRepository<PacienteEntity, Long> {
    Optional<PacienteEntity> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
}