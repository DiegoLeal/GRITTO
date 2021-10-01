package gritto.teste.controller;

import gritto.teste.service.RuaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RuaController {

    private final RuaService service;
}
