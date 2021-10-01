package gritto.teste.dto;

import gritto.teste.model.CategoriaServico;
import gritto.teste.model.Perfil;
import gritto.teste.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UsuarioDTO {
  private Long id;
  private String telefone;
  private String fullName;
  private String email;
  private CategoriaServico categoriaServico;
  private String codigoVerificador;
  private Boolean ativo;
  private Date createdAt;
  private Date updatedAt;
  private List<Perfil> perfils;

  public UsuarioDTO(Usuario user) {
    this.id = user.getId();
    this.telefone = user.getTelefone();
    this.email = user.getEmail();
    this.fullName = user.getFullName();
    this.categoriaServico = user.getCategoriaServico();
    this.codigoVerificador = user.getCodigoVerificador();
    this.ativo = user.isAtivo();
    this.createdAt = user.getCreatedAt();
    this.updatedAt = user.getUpdateAt();
    this.perfils = user.getPerfis();
  }
}
