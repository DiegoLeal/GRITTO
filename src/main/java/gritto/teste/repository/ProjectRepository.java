package gritto.teste.repository;

import gritto.teste.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findByProjectIdentifier(String projectId);

//    @Override
//    Iterable<Project> findAll();

    Iterable<Project> findAllByProjectLeader(String username);
}
