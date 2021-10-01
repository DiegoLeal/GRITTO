package gritto.teste.controller;

import gritto.teste.service.OfertaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OfertaController {

    private final OfertaService service;
}
