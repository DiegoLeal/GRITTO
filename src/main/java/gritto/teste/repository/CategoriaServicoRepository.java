package gritto.teste.repository;

import gritto.teste.model.CategoriaServico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaServicoRepository extends JpaRepository<CategoriaServico, Long> {

    @Query("select e from CategoriaServico e where e.nome like :search%")
    Page<CategoriaServico> findAllByNome(String search, Pageable pageable);
}