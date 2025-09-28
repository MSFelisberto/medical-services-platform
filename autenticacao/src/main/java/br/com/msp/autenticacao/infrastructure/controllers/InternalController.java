package br.com.msp.autenticacao.infrastructure.controllers;

import br.com.msp.autenticacao.application.dto.ValidarPacienteQuery;
import br.com.msp.autenticacao.application.ports.inbound.PacienteUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal")
public class InternalController {

    private final PacienteUseCase pacienteUseCase;

    public InternalController(PacienteUseCase pacienteUseCase) {
        this.pacienteUseCase = pacienteUseCase;
    }

    @GetMapping("/usuarios/pacientes/{pacienteId}/exists")
    public ResponseEntity<Boolean> existePaciente(@PathVariable Long pacienteId) {
        ValidarPacienteQuery query = new ValidarPacienteQuery(pacienteId);
        boolean exists = pacienteUseCase.validarPacienteExiste(query);
        return ResponseEntity.ok(exists);
    }
}