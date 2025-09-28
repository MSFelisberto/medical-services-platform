package br.com.msp.autenticacao.infrastructure.controllers;

import br.com.msp.autenticacao.application.dto.AutenticarCommand;
import br.com.msp.autenticacao.application.dto.AuthTokenOutput;
import br.com.msp.autenticacao.application.ports.inbound.AutenticacaoUseCase;
import br.com.msp.autenticacao.infrastructure.controllers.dto.ServiceAuthRequestDTO;
import br.com.msp.autenticacao.infrastructure.controllers.dto.AuthResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/service")
public class ServiceAuthController {

    private final AutenticacaoUseCase autenticacaoUseCase;

    public ServiceAuthController(AutenticacaoUseCase autenticacaoUseCase) {
        this.autenticacaoUseCase = autenticacaoUseCase;
    }

    @PostMapping("/token")
    public ResponseEntity<AuthResponseDTO> authenticateService(@RequestBody @Valid ServiceAuthRequestDTO request) {
        AutenticarCommand command = new AutenticarCommand(
                request.serviceId(),
                request.serviceSecret()
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