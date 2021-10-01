package gritto.teste.repository;

import gritto.teste.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
  Optional<Perfil> findByNome(String nome);
}
