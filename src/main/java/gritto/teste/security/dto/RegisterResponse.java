package gritto.teste.security.dto;

import gritto.teste.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RegisterResponse {
  private Long id;
  private String email;
  private Date createAt;
  private Date updateAt;

  public RegisterResponse(Usuario user) {
    this.id = user.getId();
    this.email = user.getEmail();
    this.createAt = user.getCreatedAt();
    this.updateAt = user.getUpdateAt();
  }
}
