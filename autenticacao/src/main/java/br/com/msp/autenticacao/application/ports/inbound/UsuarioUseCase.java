package br.com.msp.autenticacao.application.ports.inbound;

import br.com.msp.autenticacao.application.dto.CadastrarUsuarioCommand;
import br.com.msp.autenticacao.application.dto.UsuarioOutput;
import br.com.msp.autenticacao.application.dto.ValidarPacienteQuery;

public interface UsuarioUseCase {
    UsuarioOutput cadastrarUsuario(CadastrarUsuarioCommand command);
    boolean validarPacienteExiste(ValidarPacienteQuery query);
}