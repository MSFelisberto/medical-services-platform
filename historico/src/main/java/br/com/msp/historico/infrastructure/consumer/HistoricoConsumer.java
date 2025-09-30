package br.com.msp.historico.infrastructure.consumer;

import br.com.msp.commons.config.RabbitConfig;
import br.com.msp.commons.dtos.ConsultaDTO;
import br.com.msp.historico.application.dto.CriarHistoricoCommand;
import br.com.msp.historico.application.usecase.HistoricoUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HistoricoConsumer {
    private final HistoricoUseCase historicoUseCase;

    public HistoricoConsumer(HistoricoUseCase historicoUseCase) {
        this.historicoUseCase = historicoUseCase;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_AGENDAR)
    public void consumeAgendar(ConsultaDTO consultaDTO) {
        log.info("[AGENDAR] Mensagem recebida: {}", consultaDTO);
        this.historicoUseCase.criar(new CriarHistoricoCommand(
                consultaDTO.id(),
                consultaDTO.pacienteId(),
                consultaDTO.medicoId(),
                consultaDTO.dataHora(),
                consultaDTO.especialidade()
        ));
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_CANCELAR)
    public void consumeCancelar(ConsultaDTO consultaDTO) {
        log.info("[CANCELAR] Mensagem recebida: {}", consultaDTO);
        this.historicoUseCase.criar(new CriarHistoricoCommand(
                consultaDTO.id(),
                consultaDTO.pacienteId(),
                consultaDTO.medicoId(),
                consultaDTO.dataHora(),
                consultaDTO.especialidade()
        ));
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_REAGENDAR)
    public void consumeReagendar(ConsultaDTO consultaDTO) {
        log.info("[REAGENDAR] Mensagem recebida: {}", consultaDTO);
        this.historicoUseCase.criar(new CriarHistoricoCommand(
                consultaDTO.id(),
                consultaDTO.pacienteId(),
                consultaDTO.medicoId(),
                consultaDTO.dataHora(),
                consultaDTO.especialidade()
        ));
    }
}
