package gritto.teste.service;

import gritto.teste.repository.OfertaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfertaService {

    private final OfertaRepository repository;
}