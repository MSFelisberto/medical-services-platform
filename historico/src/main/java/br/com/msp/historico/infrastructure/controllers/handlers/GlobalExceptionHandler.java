package br.com.msp.historico.infrastructure.controllers.handlers;

import br.com.msp.historico.domain.exception.*;
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

    @ExceptionHandler(HistoricoNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleHistoricoNotFoundException(HistoricoNotFoundException ex) {
        return new ResponseEntity<>(
                Map.of("error", "Histórico Não Encontrado", "message", ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(HistoricoBusinessException.class)
    public ResponseEntity<Map<String, String>> handleHistoricoBusinessException(HistoricoBusinessException ex) {
        return new ResponseEntity<>(
                Map.of("error", "Regra de Negócio Violada", "message", ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(
                Map.of("error", "Argumento Inválido", "message", ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
}
