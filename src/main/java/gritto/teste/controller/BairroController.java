package gritto.teste.controller;

import gritto.teste.service.BairroService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BairroController {

    private final BairroService service;
}
