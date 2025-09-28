package br.com.msp.autenticacao.infrastructure.controllers;

import br.com.msp.autenticacao.application.dto.CadastrarUsuarioCommand;
import br.com.msp.autenticacao.application.dto.UsuarioOutput;
import br.com.msp.autenticacao.application.ports.inbound.UsuarioUseCase;
import br.com.msp.autenticacao.infrastructure.controllers.dto.UsuarioRequestDTO;
import br.com.msp.autenticacao.infrastructure.controllers.dto.UsuarioResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioUseCase usuarioUseCase;

    public UsuarioController(UsuarioUseCase usuarioUseCase) {
        this.usuarioUseCase = usuarioUseCase;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody @Valid UsuarioRequestDTO request) {
        CadastrarUsuarioCommand command = new CadastrarUsuarioCommand(
                request.email(),
                request.senha(),
                request.perfil()
        );

        UsuarioOutput output = usuarioUseCase.cadastrarUsuario(command);

        UsuarioResponseDTO response = new UsuarioResponseDTO(
                output.id(),
                output.email(),
                output.perfil().name()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}