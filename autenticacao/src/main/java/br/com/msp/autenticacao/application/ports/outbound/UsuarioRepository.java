package br.com.msp.autenticacao.application.ports.outbound;

import br.com.msp.autenticacao.domain.model.Usuario;
import br.com.msp.autenticacao.domain.model.UsuarioId;
import br.com.msp.autenticacao.domain.model.Email;
import java.util.Optional;

public interface UsuarioRepository {
    Usuario save(Usuario usuario);
    Optional<Usuario> findByEmail(Email email);
    Optional<Usuario> findById(UsuarioId id);
}