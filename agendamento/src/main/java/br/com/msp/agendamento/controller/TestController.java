package br.com.msp.agendamento.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste-agendamento")
public class TestController {

    @PostMapping("/medico")
    @PreAuthorize("hasRole('MEDICO')")
    public ResponseEntity<String> testeMedico(@RequestBody @Valid TestPostDTO resquestDTO) {

        return ResponseEntity.ok("Teste Medico recebido com sucesso");
    }


    @PostMapping("/enfermeiro")
    @PreAuthorize("hasRole('ENFERMEIRO')")
    public ResponseEntity<String> testeEnfermeiro(@RequestBody @Valid TestPostDTO resquestDTO) {

        return ResponseEntity.ok("Teste Medico recebido com sucesso");
    }
}
