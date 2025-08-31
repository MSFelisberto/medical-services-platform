package br.com.msp.autenticacao.application.gateways;

import br.com.msp.autenticacao.domain.Usuario;

import java.util.Optional;

public interface UsuarioGateway {
    Usuario cadastrar(Usuario usuario);
    Optional<Usuario> findByEmail(String email);
}
