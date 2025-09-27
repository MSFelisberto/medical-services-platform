package br.com.msp.agendamento.infrastructure.adapters.outbound;

import br.com.msp.agendamento.application.gateways.PacienteGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class PacienteGatewayImpl implements PacienteGateway {
    private final RestTemplate restTemplate;
    private final String pacienteServiceUrl;

    public PacienteGatewayImpl(RestTemplate restTemplate,
                               @Value("${services.paciente.url}") String pacienteServiceUrl) {
        this.restTemplate = restTemplate;
        this.pacienteServiceUrl = pacienteServiceUrl;
    }

    @Override
    public boolean existePaciente(Long pacienteId) {
        try {
            String url = pacienteServiceUrl + "/internal/usuarios/pacientes/" + pacienteId + "/exists";
            boolean exists = restTemplate.getForObject(url, Boolean.class);
            return Boolean.TRUE.equals(exists);
        } catch (HttpClientErrorException e) {
            log.warn("Paciente com ID {} não encontrado", pacienteId);
            return false;
        } catch (Exception e) {
            log.error("Erro ao verificar existência do paciente {}: {}", pacienteId, e.getMessage());
            return false;
        }
    }
}
