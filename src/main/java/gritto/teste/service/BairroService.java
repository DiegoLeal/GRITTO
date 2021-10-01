package gritto.teste.service;

import gritto.teste.repository.BairroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BairroService {

    private final BairroRepository repository;

}