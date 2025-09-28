package br.com.msp.autenticacao.application.ports.inbound;

import br.com.msp.autenticacao.application.dto.AutenticarUsuarioCommand;
import br.com.msp.autenticacao.application.dto.AuthTokenOutput;

public interface AutenticacaoUseCase {
    AuthTokenOutput autenticar(AutenticarUsuarioCommand command);
}