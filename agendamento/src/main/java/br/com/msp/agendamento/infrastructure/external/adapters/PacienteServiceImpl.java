package br.com.msp.agendamento.infrastructure.external.adapters;

import br.com.msp.agendamento.application.ports.outbound.PacienteService;
import br.com.msp.agendamento.domain.model.PacienteId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class PacienteServiceImpl implements PacienteService {

    private final RestTemplate restTemplate;
    private final String pacienteServiceUrl;

    public PacienteServiceImpl(
            RestTemplate restTemplate,
            @Value("${services.autenticacao.url}") String pacienteServiceUrl) {
        this.restTemplate = restTemplate;
        this.pacienteServiceUrl = pacienteServiceUrl;
    }

    @Override
    public boolean exists(PacienteId pacienteId) {
        try {
            String url = pacienteServiceUrl + "/internal/usuarios/pacientes/" +
                    pacienteId.getValue() + "/exists";
            Boolean exists = restTemplate.getForObject(url, Boolean.class);
            return Boolean.TRUE.equals(exists);
        } catch (HttpClientErrorException e) {
            log.warn("Paciente com ID {} não encontrado", pacienteId.getValue());
            return false;
        } catch (Exception e) {
            log.error("Erro ao verificar existência do paciente {}: {}",
                    pacienteId.getValue(), e.getMessage());
            return false;
        }
    }
}