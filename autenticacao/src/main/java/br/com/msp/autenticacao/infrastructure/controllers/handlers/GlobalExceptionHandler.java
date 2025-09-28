package br.com.msp.autenticacao.infrastructure.controllers.handlers;

import br.com.msp.autenticacao.domain.exception.EmailJaExisteException;
import br.com.msp.autenticacao.domain.exception.UsuarioBusinessException;
import br.com.msp.autenticacao.domain.exception.UsuarioNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailJaExisteException.class)
    public ResponseEntity<Map<String, String>> handleEmailJaExisteException(EmailJaExisteException ex) {
        return new ResponseEntity<>(
                Map.of("error", "E-mail já existe", "message", ex.getMessage()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUsuarioNotFoundException(UsuarioNotFoundException ex) {
        return new ResponseEntity<>(
                Map.of("error", "Usuário não encontrado", "message", ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(UsuarioBusinessException.class)
    public ResponseEntity<Map<String, String>> handleUsuarioBusinessException(UsuarioBusinessException ex) {
        return new ResponseEntity<>(
                Map.of("error", "Regra de negócio violada", "message", ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleBadCredentialsException(BadCredentialsException ex) {
        return new ResponseEntity<>(
                Map.of("error", "Credenciais inválidas", "message", "E-mail ou senha incorretos"),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(
                Map.of("error", "Argumento inválido", "message", ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
}