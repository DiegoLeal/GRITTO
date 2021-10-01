package gritto.teste.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity(name = "perfis")
public class Perfil implements GrantedAuthority {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String nome;

  @JsonIgnore
  @ManyToMany(mappedBy = "perfis")
  private List<Usuario> usuarios = new ArrayList<>();

  public Perfil(String nome) {
    this.nome = nome;
  }

  @JsonIgnore
  @Override
  public String getAuthority() {
    return this.nome;
  }

  @Override
  public String toString() {
    return this.nome;
  }
}