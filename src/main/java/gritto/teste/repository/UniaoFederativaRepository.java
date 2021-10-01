package gritto.teste.repository;

import gritto.teste.model.UniaoFederativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniaoFederativaRepository extends JpaRepository<UniaoFederativa, Long> {}
