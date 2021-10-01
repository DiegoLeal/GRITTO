package gritto.teste.controller;

import gritto.teste.dto.UsuarioDTO;
import gritto.teste.exceptions.AuthorizationException;
import gritto.teste.model.CategoriaServico;
import gritto.teste.model.Usuario;
import gritto.teste.security.service.AuthService;
import gritto.teste.service.CategoriaServicoService;
import gritto.teste.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/usuarios")
public class UsuarioController {

  private final UsuarioService usuarioService;
  private final CategoriaServicoService categoriaServicoService;

  @PreAuthorize("hasAnyRole('ADMIN')")
  @GetMapping
  public List<UsuarioDTO> getAllUsuarios() {
    return usuarioService.findAll().stream().map(UsuarioDTO::new).collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public UsuarioDTO getUserById(@PathVariable Long id) {
    var authenticatedUser = AuthService.getUserAuthenticated();

    if (authenticatedUser == null ||  !authenticatedUser.isAdmin() && !id.equals(authenticatedUser.getId())) {
      throw new AuthorizationException();
    }

    return new UsuarioDTO(usuarioService.findById(id));
  }

  @GetMapping("/categoriaservico/{id}")
  public ResponseEntity getChannelsByRecloser(@PathVariable long id) {

    CategoriaServico categoriaServico = categoriaServicoService.getById(id);
    List<Usuario> list = usuarioService.getUsuarioByCategoriaServico(categoriaServico);

    if (!list.isEmpty()) {
      return new ResponseEntity<>(list, null, HttpStatus.OK);
    }
    return new ResponseEntity<>(list, null, HttpStatus.NO_CONTENT);
  }
}