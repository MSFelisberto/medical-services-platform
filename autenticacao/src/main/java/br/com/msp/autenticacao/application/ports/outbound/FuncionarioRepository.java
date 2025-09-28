package br.com.msp.autenticacao.application.ports.outbound;

import br.com.msp.autenticacao.domain.funcionario.model.Funcionario;
import br.com.msp.autenticacao.domain.funcionario.model.FuncionarioId;
import br.com.msp.autenticacao.domain.shared.model.Email;
import java.util.Optional;

public interface FuncionarioRepository {
    Funcionario save(Funcionario funcionario);
    Optional<Funcionario> findByEmail(Email email);
    Optional<Funcionario> findById(FuncionarioId id);
    boolean existsByEmail(Email email);
    boolean existsByCpf(String cpf);
    boolean existsByCrm(String crm);
    boolean existsByCoren(String coren);
}