package br.com.msp.autenticacao.application.ports.outbound;

import br.com.msp.autenticacao.domain.model.Senha;

public interface PasswordEncoder {
    String encode(Senha senha);
    boolean matches(Senha rawPassword, String encodedPassword);
}