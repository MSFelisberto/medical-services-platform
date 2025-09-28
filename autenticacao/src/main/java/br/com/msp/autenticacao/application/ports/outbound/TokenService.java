package br.com.msp.autenticacao.application.ports.outbound;

import br.com.msp.autenticacao.domain.model.Usuario;

public interface TokenService {
    String generateToken(Usuario usuario);
    boolean validateToken(String token);
    String getEmailFromToken(String token);
    Long getUserIdFromToken(String token);
}