package gritto.teste.security;

import gritto.teste.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Aqui ficam todos os dados do usuário logado.
 * Não misturar o Model Usuario com Segurança pois estão em diferentes contextos e camadas
 *
 * @Author: Thiago Alves <thiago_marketingdigital@hotmail.com>
 */
public class UserSecurity extends Usuario implements UserDetails {

  public UserSecurity(Usuario user) {
    super(user);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return super.getPerfis();
  }

  public List<String> getRoles() {
    return this.getPerfis().stream().map( perfil -> perfil.getNome()).collect(Collectors.toList());
  }

  public boolean isAdmin() {
    return this.getRoles().contains("ROLE_ADMIN");
  }

  @Override
  public String getPassword() {
    return super.getPassword();
  }

  @Override
  public String getUsername() {
    return super.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return super.isAtivo();
  }
}
