package br.com.msp.autenticacao.infrastructure.security;

import br.com.msp.autenticacao.application.ports.outbound.FuncionarioRepository;
import br.com.msp.autenticacao.application.ports.outbound.PacienteRepository;
import br.com.msp.autenticacao.domain.funcionario.model.Funcionario;
import br.com.msp.autenticacao.domain.paciente.model.Paciente;
import br.com.msp.autenticacao.domain.shared.model.Email;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PacienteRepository pacienteRepository;
    private final FuncionarioRepository funcionarioRepository;

    public UserDetailsServiceImpl(PacienteRepository pacienteRepository,
                                  FuncionarioRepository funcionarioRepository) {
        this.pacienteRepository = pacienteRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Email emailVO = new Email(email);

        // Tentar encontrar paciente primeiro
        Optional<Paciente> pacienteOpt = pacienteRepository.findByEmail(emailVO);
        if (pacienteOpt.isPresent()) {
            Paciente paciente = pacienteOpt.get();
            return new CustomUserDetails(
                    paciente.getId().getValue(),
                    paciente.getEmail().getValue(),
                    paciente.getSenha().getValue(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_PACIENTE"))
            );
        }

        // Tentar encontrar funcionário
        Optional<Funcionario> funcionarioOpt = funcionarioRepository.findByEmail(emailVO);
        if (funcionarioOpt.isPresent()) {
            Funcionario funcionario = funcionarioOpt.get();
            return new CustomUserDetails(
                    funcionario.getId().getValue(),
                    funcionario.getEmail().getValue(),
                    funcionario.getSenha().getValue(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + funcionario.getTipo().name()))
            );
        }

        throw new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email);
    }
}