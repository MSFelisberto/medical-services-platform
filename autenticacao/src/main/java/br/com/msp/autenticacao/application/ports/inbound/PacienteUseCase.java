package br.com.msp.autenticacao.application.ports.inbound;

import br.com.msp.autenticacao.application.dto.CadastrarPacienteCommand;
import br.com.msp.autenticacao.application.dto.PacienteOutput;
import br.com.msp.autenticacao.application.dto.ValidarPacienteQuery;

public interface PacienteUseCase {
    PacienteOutput cadastrarPaciente(CadastrarPacienteCommand command);
    boolean validarPacienteExiste(ValidarPacienteQuery query);
    PacienteOutput buscarPorId(Long id);
}