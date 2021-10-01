package gritto.teste.security.service;

import gritto.teste.exceptions.AuthorizationException;
import gritto.teste.exceptions.BusinessRuleException;
import gritto.teste.model.Usuario;
import gritto.teste.repository.PerfilRepository;
import gritto.teste.repository.UsuarioRepository;
import gritto.teste.security.UserSecurity;
import gritto.teste.security.config.JwtTokenProvider;
import gritto.teste.security.dto.LoginRequest;
import gritto.teste.security.dto.LoginResponse;
import gritto.teste.security.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static gritto.teste.security.SecurityConstants.TOKEN_PREFIX;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UsuarioRepository usuarioRepository;
  private final PerfilRepository perfilRepository;
  private final JwtTokenProvider tokenProvider;
  private final AuthenticationManager authenticationManager;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public static UserSecurity getUserAuthenticated() {
    try {
      return (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    } catch (Exception ex) {
      return null;
    }
  }

  public LoginResponse login(LoginRequest login) {

    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    login.getEmail(),
                    login.getPassword()
            )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);

    return new LoginResponse(jwt);
  }

  public Usuario register(RegisterRequest account) {

    var authenticatedUser = AuthService.getUserAuthenticated();
    var isCommonUser = (authenticatedUser == null || !authenticatedUser.isAdmin());

    if (isCommonUser && account.getPerfis().contains("ROLE_ADMIN")) {
      throw new AuthorizationException(
              "Somente um administrador pode registrar outra conta com privilégios administrativos"
      );

    } else {

      var usuario = account.toModel();

      usuarioRepository.findByEmail(usuario.getEmail()).ifPresent( function -> {
        throw new BusinessRuleException("já existe um usuário cadastrado com o email informado");
      });

      var perfis = perfilRepository.findAll().stream()
              .filter(role -> account.getPerfis().contains(role.getNome()))
              .collect(Collectors.toList());

      if (perfis.isEmpty()) {
        throw new BusinessRuleException("Não é possível registar um novo usuário sem um perfil válido");
      }

      usuario.setPerfis(perfis);
      usuario.setAtivo(true);
      usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));

      return usuarioRepository.save(usuario);
    }
  }

}
