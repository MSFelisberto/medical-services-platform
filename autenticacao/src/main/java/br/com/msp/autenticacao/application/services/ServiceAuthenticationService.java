package br.com.msp.autenticacao.application.services;

import br.com.msp.autenticacao.infrastructure.security.adapters.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ServiceAuthenticationService {

    private final TokenServiceImpl tokenService;
    private final Map<String, String> serviceCredentials;

    public ServiceAuthenticationService(
            TokenServiceImpl tokenService,
            @Value("${services.credentials.agendamento.id}") String agendamentoId,
            @Value("${services.credentials.agendamento.secret}") String agendamentoSecret,
            @Value("${services.credentials.historico.id}") String historicoId,
            @Value("${services.credentials.historico.secret}") String historicoSecret) {

        this.tokenService = tokenService;
        this.serviceCredentials = Map.of(
                agendamentoId, agendamentoSecret,
                historicoId, historicoSecret
        );
    }

    public String authenticateService(String serviceId, String serviceSecret) {
        if (serviceCredentials.containsKey(serviceId) &&
                serviceCredentials.get(serviceId).equals(serviceSecret)) {
            return tokenService.generateServiceToken(serviceId);
        }
        throw new IllegalArgumentException("Credenciais de serviço inválidas");
    }
}
