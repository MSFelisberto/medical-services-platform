package br.com.msp.autenticacao.infrastructure.controllers;

import br.com.msp.autenticacao.application.dto.CadastrarPacienteCommand;
import br.com.msp.autenticacao.application.dto.PacienteOutput;
import br.com.msp.autenticacao.application.ports.inbound.PacienteUseCase;
import br.com.msp.autenticacao.infrastructure.controllers.dto.PacienteRequestDTO;
import br.com.msp.autenticacao.infrastructure.controllers.dto.PacienteResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteUseCase pacienteUseCase;

    public PacienteController(PacienteUseCase pacienteUseCase) {
        this.pacienteUseCase = pacienteUseCase;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PacienteResponseDTO> cadastrar(@RequestBody @Valid PacienteRequestDTO request) {
        CadastrarPacienteCommand command = request.toCommand();
        PacienteOutput output = pacienteUseCase.cadastrarPaciente(command);
        PacienteResponseDTO response = PacienteResponseDTO.fromOutput(output);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDICO', 'ENFERMEIRO')")
    public ResponseEntity<PacienteResponseDTO> buscarPorId(@PathVariable Long id) {
        PacienteOutput output = pacienteUseCase.buscarPorId(id);
        PacienteResponseDTO response = PacienteResponseDTO.fromOutput(output);

        return ResponseEntity.ok(response);
    }
}