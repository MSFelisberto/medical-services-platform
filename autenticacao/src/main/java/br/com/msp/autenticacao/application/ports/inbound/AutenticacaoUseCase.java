package br.com.msp.autenticacao.application.ports.inbound;

import br.com.msp.autenticacao.application.dto.AutenticarCommand;
import br.com.msp.autenticacao.application.dto.AuthTokenOutput;

public interface AutenticacaoUseCase {
    AuthTokenOutput autenticar(AutenticarCommand command);
}
