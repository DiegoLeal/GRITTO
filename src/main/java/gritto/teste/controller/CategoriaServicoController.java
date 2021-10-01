package gritto.teste.controller;

import gritto.teste.model.CategoriaServico;
import gritto.teste.service.CategoriaServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("categoriaservicos")
public class CategoriaServicoController {

    private final CategoriaServicoService service;

    @GetMapping("/datatables/server")
    public ResponseEntity<?> getCategoriaServicos(HttpServletRequest request) {

        return ResponseEntity.ok(service.buscarCategoriasServicos(request));
    }

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaServico adicionarCategoriaServico(@RequestBody CategoriaServico categoriaServico) {

        return service.adicionarCategoriaServico(categoriaServico);
    }
}