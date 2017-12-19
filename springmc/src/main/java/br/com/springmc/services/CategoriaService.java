package br.com.springmc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.springmc.domain.Categoria;
import br.com.springmc.dto.CategoriaDTO;
import br.com.springmc.repositories.CategoriaRepository;
import br.com.springmc.services.exception.CategoriaNegocioException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRespository;
	
	public Categoria find(Integer idCategoria) {
		Categoria categoria = categoriaRespository.findOne(idCategoria);
		
		if(categoria == null) {
			throw new CategoriaNegocioException("Categoria não encontrada.",HttpStatus.NOT_FOUND);
		}
		return categoria;
	}

	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return categoriaRespository.save(categoria);
	}

	public Categoria update(Categoria categoria) {
		find(categoria.getId());
		return categoriaRespository.save(categoria);
	}

	public void delete(Integer id) {
		find(id);
		try {
			categoriaRespository.delete(id);
		}catch (DataIntegrityViolationException e) {
			throw new CategoriaNegocioException("Não é possível excluir uma categoria que possui produtos.", HttpStatus.BAD_REQUEST);
		}
		
	}

	public List<Categoria> findAll() {
		return categoriaRespository.findAll();
		
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoriaRespository.findAll(pageRequest);
	}

	public Categoria fromDTO(CategoriaDTO dto) {
		return new Categoria(dto.getId(), dto.getNome());
	}
}
