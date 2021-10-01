package gritto.teste.dto.input;

import gritto.teste.model.Perfil;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PerfilInput {

  @NotBlank
  private String nome;

  public Perfil toModel() {
    return new Perfil(nome);
  }
}
