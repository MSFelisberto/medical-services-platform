package br.com.msp.autenticacao.application.services;

import br.com.msp.autenticacao.application.dto.AutenticarUsuarioCommand;
import br.com.msp.autenticacao.application.dto.AuthTokenOutput;
import br.com.msp.autenticacao.application.ports.inbound.AutenticacaoUseCase;
import br.com.msp.autenticacao.application.ports.outbound.PasswordEncoder;
import br.com.msp.autenticacao.application.ports.outbound.TokenService;
import br.com.msp.autenticacao.application.ports.outbound.UsuarioRepository;
import br.com.msp.autenticacao.domain.exception.UsuarioNotFoundException;
import br.com.msp.autenticacao.domain.model.Email;
import br.com.msp.autenticacao.domain.model.Senha;
import br.com.msp.autenticacao.domain.model.Usuario;
import org.springframework.security.authentication.BadCredentialsException;

public class AutenticacaoUseCaseImpl implements AutenticacaoUseCase {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AutenticacaoUseCaseImpl(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Override
    public AuthTokenOutput autenticar(AutenticarUsuarioCommand command) {
        Email email = new Email(command.email());
        Senha senhaRaw = new Senha(command.senha());

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsuarioNotFoundException("Credenciais inválidas"));

        if (!passwordEncoder.matches(senhaRaw, usuario.getSenha().getValue())) {
            throw new BadCredentialsException("Credenciais inválidas");
        }

        String token = tokenService.generateToken(usuario);

        return new AuthTokenOutput(
                token,
                "Bearer",
                86400000L // 24 horas em millisegundos
        );
    }
}