package gritto.teste.service;

import gritto.teste.repository.CidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CidadeService {

    private final CidadeRepository repository;
}