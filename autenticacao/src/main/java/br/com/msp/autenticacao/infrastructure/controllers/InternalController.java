package br.com.msp.autenticacao.infrastructure.controllers;

import br.com.msp.autenticacao.application.usecases.ValidarPacienteUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal")
public class InternalController {

    private final ValidarPacienteUseCase validarPacienteUseCase;

    public InternalController(ValidarPacienteUseCase validarPacienteUseCase) {
        this.validarPacienteUseCase = validarPacienteUseCase;
    }

    @GetMapping("/usuarios/pacientes/{pacienteId}/exists")
    public ResponseEntity<Boolean> existePaciente(@PathVariable Long pacienteId) {
        boolean exists = validarPacienteUseCase.existsPaciente(pacienteId);
        return ResponseEntity.ok(exists);
    }
}
