package gritto.teste.service;

import gritto.teste.repository.RuaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RuaService {

    private final RuaRepository repository;

}
