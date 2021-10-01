package gritto.teste.security.dto;
import gritto.teste.model.Perfil;
import gritto.teste.model.Usuario;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class RegisterRequest {

  @NotBlank
  private String fullName;

  @Email
  @NotBlank
  private String email;

  @NotBlank
  private String password;

  @NotEmpty
  private Set<String> perfis;

  public Usuario toModel() {
    var perfisList = perfis
            .stream()
            .map(Perfil::new)
            .collect(Collectors.toList());

    return Usuario.builder()
            .fullName(fullName)
            .email(email)
            .password(password)
            .perfis(perfisList)
            .build();
  }

}
