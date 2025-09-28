package br.com.msp.autenticacao.infrastructure.controllers;

import br.com.msp.autenticacao.application.dto.AutenticarCommand;
import br.com.msp.autenticacao.application.dto.AuthTokenOutput;
import br.com.msp.autenticacao.application.ports.inbound.AutenticacaoUseCase;
import br.com.msp.autenticacao.infrastructure.controllers.dto.AuthRequestDTO;
import br.com.msp.autenticacao.infrastructure.controllers.dto.AuthResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AutenticacaoUseCase autenticacaoUseCase;

    public AuthController(AutenticacaoUseCase autenticacaoUseCase) {
        this.autenticacaoUseCase = autenticacaoUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthRequestDTO request) {
        AutenticarCommand command = new AutenticarCommand(
                request.email(),
                request.senha()
        );

        AuthTokenOutput output = autenticacaoUseCase.autenticar(command);

        AuthResponseDTO response = new AuthResponseDTO(
                output.token(),
                output.type(),
                output.expiresIn(),
                output.userType()
        );

        return ResponseEntity.ok(response);
    }
}