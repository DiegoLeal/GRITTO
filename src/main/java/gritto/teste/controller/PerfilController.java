package gritto.teste.controller;

import gritto.teste.dto.input.PerfilInput;
import gritto.teste.model.Perfil;
import gritto.teste.service.PerfilService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/perfis")
public class PerfilController {

  private final PerfilService perfilService;

  @PostMapping
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<Perfil> criarPerfil(@Valid @RequestBody PerfilInput perfil) {
    return new ResponseEntity<>(
            perfilService.save(perfil.toModel()),
            HttpStatus.CREATED
    );
  }

  @GetMapping
  public ResponseEntity<List<Perfil>> findAll() {
    return ResponseEntity.ok(perfilService.findAll());
  }
}
