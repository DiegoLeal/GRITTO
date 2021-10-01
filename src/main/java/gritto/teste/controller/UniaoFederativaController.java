package gritto.teste.controller;

import gritto.teste.model.UniaoFederativa;
import gritto.teste.repository.UniaoFederativaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/estados")
public class UniaoFederativaController {

    private final UniaoFederativaRepository repository;

    public UniaoFederativaController(UniaoFederativaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Page<UniaoFederativa> estados(final Pageable page) {

        return repository.findAll(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity getEstados(@PathVariable Long id) {
        Optional<UniaoFederativa> optional = repository.findById(id);

        if (optional.isPresent()) {
            return ResponseEntity.ok().body(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
