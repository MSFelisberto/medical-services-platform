package br.com.msp.autenticacao.domain.paciente.exception;

public class PacienteBusinessException extends RuntimeException {
    public PacienteBusinessException(String message) {
        super(message);
    }

    public PacienteBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}