package br.com.msp.autenticacao.application.ports.outbound;

import br.com.msp.autenticacao.domain.paciente.model.Paciente;
import br.com.msp.autenticacao.domain.paciente.model.PacienteId;
import br.com.msp.autenticacao.domain.shared.model.Email;
import java.util.Optional;

public interface PacienteRepository {
    Paciente save(Paciente paciente);
    Optional<Paciente> findByEmail(Email email);
    Optional<Paciente> findById(PacienteId id);
    boolean existsByEmail(Email email);
    boolean existsByCpf(String cpf);
}