package gritto.teste.service;

import gritto.teste.exceptions.BusinessRuleException;
import gritto.teste.model.Perfil;
import gritto.teste.repository.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerfilService {

  private final PerfilRepository perfilRepository;

  public Perfil save(Perfil perfil) {

    perfil.setNome(perfil.getNome().toUpperCase());

    if (!perfil.getNome().startsWith("ROLE_"))
      perfil.setNome("ROLE_" + perfil.getNome());

    perfilRepository.findByNome(perfil.getNome()).ifPresent( function -> {
      throw new BusinessRuleException("Já existe um perfil com esse nome");
    });

    return perfilRepository.save(perfil);
  }

  public List<Perfil> findAll() {
    return perfilRepository.findAll();
  }

}
