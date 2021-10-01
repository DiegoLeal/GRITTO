package gritto.teste.controller;

import gritto.teste.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService service;
}