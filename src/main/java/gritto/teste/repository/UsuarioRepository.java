package gritto.teste.repository;

import gritto.teste.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario>findByCategoriaServico(Long id);
    Optional<Usuario> findByEmail(String email);

    //Usuario findByUsername(String username);
    Usuario getById(Long id);

}