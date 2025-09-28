package br.com.msp.autenticacao.infrastructure.controllers;

import br.com.msp.autenticacao.application.services.ServiceAuthenticationService;
import br.com.msp.autenticacao.infrastructure.controllers.dto.ServiceAuthRequestDTO;
import br.com.msp.autenticacao.infrastructure.controllers.dto.AuthResponseDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth/service")
public class ServiceAuthController {

    private final ServiceAuthenticationService serviceAuthenticationService;

    public ServiceAuthController(ServiceAuthenticationService serviceAuthenticationService) {
        this.serviceAuthenticationService = serviceAuthenticationService;
    }

    @PostMapping("/token")
    public ResponseEntity<AuthResponseDTO> authenticateService(@RequestBody @Valid ServiceAuthRequestDTO request) {
        log.info("Tentativa de autenticação de serviço: {}", request.serviceId());

        try {
            String token = serviceAuthenticationService.authenticateService(
                    request.serviceId(),
                    request.serviceSecret()
            );

            AuthResponseDTO response = new AuthResponseDTO(
                    token,
                    "Bearer",
                    3600000L,
                    "SISTEMA"
            );

            log.info("Serviço autenticado com sucesso: {}", request.serviceId());
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Erro na autenticação do serviço {}: {}", request.serviceId(), e.getMessage());
            throw e;
        }
    }
}