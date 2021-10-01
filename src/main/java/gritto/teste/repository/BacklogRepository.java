package gritto.teste.repository;


import gritto.teste.model.Backlog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BacklogRepository extends JpaRepository<Backlog, Long> {

    Backlog findByProjectIdentifier(String Identifier);
}
