package br.com.msp.notificacoes.infrastructure.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste-notificacoes")
public class ControllerTest {

    @PostMapping("/medico")
    @PreAuthorize("hasRole('MEDICO')")
    public ResponseEntity<String> testeMedico() {
        return ResponseEntity.ok("Teste Medico recebido com sucesso");
    }

    @PostMapping("/enfermeiro")
    @PreAuthorize("hasRole('ENFERMEIRO')")
    public ResponseEntity<String> testeEnfermeiro() {
        return ResponseEntity.ok("Teste Enfermeiro recebido com sucesso");
    }

    @PostMapping("/paciente")
    @PreAuthorize("hasRole('PACIENTE')")
    public ResponseEntity<String> testePaciente() {
        return ResponseEntity.ok("Teste Paciente recebido com sucesso");
    }
}
