package br.com.msp.autenticacao.domain.exception;

public class UsuarioBusinessException extends RuntimeException {
    public UsuarioBusinessException(String message) {
        super(message);
    }

    public UsuarioBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}