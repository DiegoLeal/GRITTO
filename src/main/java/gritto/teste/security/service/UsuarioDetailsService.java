package gritto.teste.security.service;

import gritto.teste.model.Usuario;
import gritto.teste.repository.UsuarioRepository;
import gritto.teste.security.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioDetailsService implements UserDetailsService {

  private final UsuarioRepository usuarioRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    var user = usuarioRepository
            .findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("usuário não encontrado"));

    return new UserSecurity(user);
  }

  @Transactional
  public UserDetails loadUserById(Long id) {
    Usuario usuario = usuarioRepository.getById(id);
    if (usuario == null) throw new UsernameNotFoundException("usuário não encontrado");
    return new UserSecurity(usuario);
  }
}