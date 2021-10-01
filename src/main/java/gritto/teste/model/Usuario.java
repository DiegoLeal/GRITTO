package gritto.teste.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "usuarios")
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String fullName;

  @Column(unique = true)
  private String email;
  private String password;
  private String telefone;

  @Column(length = 6)
  private String codigoVerificador;

  private boolean ativo;

  @CreationTimestamp
  private Date createdAt;
  @UpdateTimestamp
  private Date updateAt;

  @ManyToOne
  @JoinColumn(name = "id_categoriaServico")
  private CategoriaServico categoriaServico;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "usuarios_tem_perfis",
          joinColumns = @JoinColumn(name = "usuario_id"),
          inverseJoinColumns = @JoinColumn(name = "perfil_id"))
  private List<Perfil> perfis = new ArrayList<>();

  /**
   * Construtor utilizado para carregar as informações de segurança.
   * @Author: Thiago Alves Oliveira
   * @param user
   */
  public Usuario(Usuario user) {
    this.id = user.getId();
    this.telefone = user.getTelefone();
    this.email = user.getEmail();
    this.fullName = user.getFullName();
    this.password = user.getPassword();
    this.createdAt = user.getCreatedAt();
    this.updateAt = user.getUpdateAt();
    this.categoriaServico = user.getCategoriaServico();
    this.perfis = user.getPerfis();
    this.ativo = user.isAtivo();
    this.codigoVerificador = user.getCodigoVerificador();
  }

}