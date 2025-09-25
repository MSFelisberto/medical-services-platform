package br.com.msp.notificacoes.infrastructure.service;

import br.com.msp.notificacoes.infrastructure.dtos.ConsultaDTO;
import br.com.msp.notificacoes.infrastructure.producer.NotificationProducer;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    private final NotificationProducer notificationProducer;

    public ConsultaService(NotificationProducer notificationProducer) {
        this.notificationProducer = notificationProducer;
    }

    public void agendarConsulta(ConsultaDTO consultaDTO) {

        System.out.println("Consulta agendada: " + consultaDTO);
        notificationProducer.send(consultaDTO);
    }
}