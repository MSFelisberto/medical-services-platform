package br.com.msp.autenticacao.application.ports.inbound;

import br.com.msp.autenticacao.application.dto.CadastrarFuncionarioCommand;
import br.com.msp.autenticacao.application.dto.FuncionarioOutput;

public interface FuncionarioUseCase {
    FuncionarioOutput cadastrarFuncionario(CadastrarFuncionarioCommand command);
    FuncionarioOutput buscarPorId(Long id);
}