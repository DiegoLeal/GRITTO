package gritto.teste.controller;

import gritto.teste.service.PublicacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PublicacaoController {

    private final PublicacaoService service;
}
