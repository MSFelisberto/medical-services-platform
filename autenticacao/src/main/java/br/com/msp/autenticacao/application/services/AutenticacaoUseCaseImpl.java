package br.com.msp.autenticacao.application.services;

import br.com.msp.autenticacao.application.dto.AutenticarCommand;
import br.com.msp.autenticacao.application.dto.AuthTokenOutput;
import br.com.msp.autenticacao.application.ports.inbound.AutenticacaoUseCase;
import br.com.msp.autenticacao.application.ports.outbound.*;
import br.com.msp.autenticacao.domain.paciente.model.Paciente;
import br.com.msp.autenticacao.domain.funcionario.model.Funcionario;
import br.com.msp.autenticacao.domain.shared.model.Email;
import br.com.msp.autenticacao.domain.shared.model.Senha;
import org.springframework.security.authentication.BadCredentialsException;

public class AutenticacaoUseCaseImpl implements AutenticacaoUseCase {

    private final PacienteRepository pacienteRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AutenticacaoUseCaseImpl(
            PacienteRepository pacienteRepository,
            FuncionarioRepository funcionarioRepository,
            PasswordEncoder passwordEncoder,
            TokenService tokenService) {
        this.pacienteRepository = pacienteRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Override
    public AuthTokenOutput autenticar(AutenticarCommand command) {
        Email email = new Email(command.email());
        Senha senhaRaw = new Senha(command.senha());

        // Tentar autenticar como paciente primeiro
        var pacienteOpt = pacienteRepository.findByEmail(email);
        if (pacienteOpt.isPresent()) {
            Paciente paciente = pacienteOpt.get();
            if (passwordEncoder.matches(senhaRaw, paciente.getSenha().getValue())) {
                String token = tokenService.generateTokenForPaciente(paciente);
                return new AuthTokenOutput(token, "Bearer", 86400000L, "PACIENTE");
            }
        }

        // Tentar autenticar como funcionário
        var funcionarioOpt = funcionarioRepository.findByEmail(email);
        if (funcionarioOpt.isPresent()) {
            Funcionario funcionario = funcionarioOpt.get();
            if (passwordEncoder.matches(senhaRaw, funcionario.getSenha().getValue())) {
                String token = tokenService.generateTokenForFuncionario(funcionario);
                return new AuthTokenOutput(token, "Bearer", 86400000L, "FUNCIONARIO");
            }
        }

        throw new BadCredentialsException("Credenciais inválidas");
    }
}