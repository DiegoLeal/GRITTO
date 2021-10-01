package gritto.teste.service;

import gritto.teste.repository.UniaoFederativaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UniaoFederativaService {

    private final UniaoFederativaRepository repository;

}
