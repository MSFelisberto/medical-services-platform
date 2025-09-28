package br.com.msp.autenticacao.infrastructure.security;

import br.com.msp.autenticacao.application.ports.outbound.UsuarioRepository;
import br.com.msp.autenticacao.domain.model.Email;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Email emailVO = new Email(email);

        return usuarioRepository.findByEmail(emailVO)
                .map(usuario -> new CustomUserDetails(
                        usuario.getId().getValue(),
                        usuario.getEmail().getValue(),
                        usuario.getSenha().getValue(),
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.getPerfil().name()))
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email));
    }
}