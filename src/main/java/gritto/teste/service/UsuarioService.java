package gritto.teste.service;

import gritto.teste.exceptions.ResourceNotFoundException;
import gritto.teste.model.CategoriaServico;
import gritto.teste.model.Usuario;
import gritto.teste.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

  private final UsuarioRepository usuarioRepository;

  public List<Usuario> findAll() {
    return usuarioRepository.findAll();
  }

  public Usuario findById(Long id) {
    Usuario usuario = usuarioRepository.getById(id);
    if (usuario == null) throw new ResourceNotFoundException("usuário não encontrado");
    return usuario;
  }

  public Usuario findByEmail(String email) {
    return usuarioRepository
            .findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("usuário não encontrado"));
  }

  public List<Usuario> getUsuarioByCategoriaServico(Long id) {
    return usuarioRepository.findByCategoriaServico(id);
  }

  public List<Usuario> getUsuarioByCategoriaServico(CategoriaServico categoriaServico) {
    List<Usuario> usuarios = usuarioRepository.findAll();

    return usuarios.stream()
            .filter(usuario -> usuario.getCategoriaServico().equals(categoriaServico))
            .collect(Collectors.toList());
  }

}
