package br.com.msp.agendamento.infrastructure.controllers;

import br.com.msp.agendamento.application.dto.ReagendarConsultaInput;
import br.com.msp.agendamento.application.usecases.AgendarConsultaUseCase;
import br.com.msp.agendamento.application.usecases.CancelarConsultaUseCase;
import br.com.msp.agendamento.application.usecases.ListarConsultasPorPacienteUseCase;
import br.com.msp.agendamento.application.usecases.ReagendarConsultaUseCase;
import br.com.msp.agendamento.infrastructure.controllers.dto.ConsultaRequestDTO;
import br.com.msp.agendamento.infrastructure.controllers.dto.ConsultaResponseDTO;
import br.com.msp.agendamento.infrastructure.controllers.mappers.ConsultaDTOMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {
    private final AgendarConsultaUseCase agendarConsultaUseCase;
    private final ReagendarConsultaUseCase reagendarConsultaUseCase;
    private final CancelarConsultaUseCase cancelarConsultaUseCase;
    private final ListarConsultasPorPacienteUseCase listarConsultasPorPacienteUseCase;
    private final ConsultaDTOMapper mapper;

    public AgendamentoController(AgendarConsultaUseCase agendarConsultaUseCase,
                                 ReagendarConsultaUseCase reagendarConsultaUseCase,
                                 CancelarConsultaUseCase cancelarConsultaUseCase,
                                 ListarConsultasPorPacienteUseCase listarConsultasPorPacienteUseCase,
                                 ConsultaDTOMapper mapper) {
        this.agendarConsultaUseCase = agendarConsultaUseCase;
        this.reagendarConsultaUseCase = reagendarConsultaUseCase;
        this.cancelarConsultaUseCase = cancelarConsultaUseCase;
        this.listarConsultasPorPacienteUseCase = listarConsultasPorPacienteUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('MEDICO', 'ENFERMEIRO')")
    public ResponseEntity<ConsultaResponseDTO> agendar(
            @RequestBody @Valid ConsultaRequestDTO requestDTO
    ) {
        var input = mapper.toInput(requestDTO);
        var output = agendarConsultaUseCase.executar(input);
        var response = mapper.toResponse(output);
        URI uri = URI.create("/agendamento" + response.id());
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('MEDICO', 'ENFERMEIRO')")
    public ResponseEntity<ConsultaResponseDTO> reagendar(
            @PathVariable Long id,
            @RequestBody @Valid ConsultaRequestDTO requestDTO
    ) {
        ReagendarConsultaInput input = mapper.toReagendarInput(id, requestDTO);
        var output = reagendarConsultaUseCase.executar(input);
        return ResponseEntity.ok(mapper.toResponse(output));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('MEDICO', 'ENFERMEIRO')")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        cancelarConsultaUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paciente/{pacienteId}")
    @PreAuthorize("hasAnyRole('MEDICO', 'ENFERMEIRO', 'PACIENTE')")
    public ResponseEntity<List<ConsultaResponseDTO>> listarPorPaciente(
            @PathVariable Long pacienteId
    ) {
        List<ConsultaResponseDTO> response = listarConsultasPorPacienteUseCase.executar(pacienteId)
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
