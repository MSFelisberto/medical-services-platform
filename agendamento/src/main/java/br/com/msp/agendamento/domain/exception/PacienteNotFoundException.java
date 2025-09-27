package br.com.msp.agendamento.domain.exception;

public class PacienteNotFoundException extends RuntimeException {
    public PacienteNotFoundException(String message) {
        super(message);
    }

    public PacienteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
