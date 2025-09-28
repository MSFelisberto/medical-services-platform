package br.com.msp.agendamento.infrastructure.external.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Service
public class AuthenticationService {

    private final RestTemplate restTemplate;
    private final String authServiceUrl;
    private final String serviceId;
    private final String serviceSecret;

    private String cachedToken;
    private long tokenExpirationTime;

    public AuthenticationService(
            RestTemplate restTemplate,
            @Value("${services.autenticacao.url}") String authServiceUrl,
            @Value("${service.id}") String serviceId,
            @Value("${service.secret}") String serviceSecret) {

        this.restTemplate = restTemplate;
        this.authServiceUrl = authServiceUrl;
        this.serviceId = serviceId;
        this.serviceSecret = serviceSecret;
    }

    public String getServiceToken() {
        if (isTokenValid()) {
            return cachedToken;
        }

        return authenticateService();
    }

    private boolean isTokenValid() {
        return cachedToken != null && System.currentTimeMillis() < tokenExpirationTime;
    }

    private String authenticateService() {
        try {
            String url = authServiceUrl + "/auth/service/token";

            Map<String, String> request = Map.of(
                    "serviceId", serviceId,
                    "serviceSecret", serviceSecret
            );

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, String>> entity = new HttpEntity<>(request, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                Map<String, Object> responseBody = response.getBody();
                cachedToken = (String) responseBody.get("token");
                Long expiresIn = (Long) responseBody.get("expiresIn");
                tokenExpirationTime = System.currentTimeMillis() + (expiresIn - 60000); // 1min buffer

                log.info("Token de serviço obtido com sucesso");
                return cachedToken;
            }

            throw new RuntimeException("Falha na autenticação do serviço");

        } catch (Exception e) {
            log.error("Erro ao obter token de serviço: {}", e.getMessage());
            throw new RuntimeException("Não foi possível autenticar o serviço", e);
        }
    }
}