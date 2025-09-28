package br.com.msp.autenticacao.infrastructure.controllers;

import br.com.msp.autenticacao.application.dto.ValidarPacienteQuery;
import br.com.msp.autenticacao.application.ports.inbound.UsuarioUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal")
public class InternalController {

    private final UsuarioUseCase usuarioUseCase;

    public InternalController(UsuarioUseCase usuarioUseCase) {
        this.usuarioUseCase = usuarioUseCase;
    }

    @GetMapping("/usuarios/pacientes/{pacienteId}/exists")
    public ResponseEntity<Boolean> existePaciente(@PathVariable Long pacienteId) {
        ValidarPacienteQuery query = new ValidarPacienteQuery(pacienteId);
        boolean exists = usuarioUseCase.validarPacienteExiste(query);
        return ResponseEntity.ok(exists);
    }
}