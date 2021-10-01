package gritto.teste.service;

import gritto.teste.datatables.Datatables;
import gritto.teste.datatables.DatatablesColunas;
import gritto.teste.model.CategoriaServico;
import gritto.teste.repository.CategoriaServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CategoriaServicoService {

    private final CategoriaServicoRepository repository;

    private final Datatables datatables;

    @Transactional(readOnly = false)
    public CategoriaServico adicionarCategoriaServico(CategoriaServico categoriaServico) {

       return repository.save(categoriaServico);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> buscarCategoriasServicos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.CATEGORIASERICO);
        Page<?> page = datatables.getSearch().isEmpty()
                ? repository.findAll(datatables.getPageable())
                : repository.findAllByNome(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    public CategoriaServico getById(long id) {
        return repository.getOne(id);
    }

}
