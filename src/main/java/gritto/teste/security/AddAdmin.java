package gritto.teste.security;

import gritto.teste.model.Perfil;
import gritto.teste.model.Usuario;
import gritto.teste.repository.PerfilRepository;
import gritto.teste.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class AddAdmin implements ApplicationListener<ContextRefreshedEvent> {

  private final UsuarioRepository usuarioRepository;
  private final PerfilRepository perfilRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

    var perfis = perfilRepository.findAll();

    if (perfis.isEmpty()) {
      perfilRepository.saveAll(List.of(
              new Perfil("ROLE_ADMIN"),
              new Perfil("ROLE_TOMADOR"),
              new Perfil("ROLE_PRESTADOR")
      ));

      perfis.addAll(perfilRepository.findAll());
    }

    var admin = new Usuario();
    admin.setFullName("Admin");
    admin.setEmail("admin@admin.com");
    admin.setPassword(bCryptPasswordEncoder.encode("12345678"));
    admin.setAtivo(true);

    admin.getPerfis().addAll(perfis);
    usuarioRepository.save(admin);
  }
}
