package br.com.msp.agendamento.infrastructure.controllers.handlers;

import br.com.msp.agendamento.domain.exception.AuthorizationException;
import br.com.msp.agendamento.domain.exception.PacienteNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<Map<String, String>> handleAuthorizationException(AuthorizationException ex) {
        return new ResponseEntity<>(
                Map.of("error", "Acesso Negado", "message", ex.getMessage()),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(
                Map.of("error", "Recurso Não Encontrado", "message", ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(PacienteNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePacienteNotFoundException(PacienteNotFoundException ex) {
        return new ResponseEntity<>(
                Map.of("error", "Paciente Não Encontrado", "message", ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
}
