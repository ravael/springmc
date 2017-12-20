package br.com.springmc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.springmc.domain.Categoria;
import br.com.springmc.domain.Produto;
import br.com.springmc.repositories.CategoriaRepository;
import br.com.springmc.repositories.ProdutoRepository;
import br.com.springmc.services.exception.ProdutoException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Page<Produto> search(String nome, List<Integer> ids,Integer page,Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		List<Categoria> categorias = categoriaRepository.findAll(ids);
		
		return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome,categorias,pageRequest);
	}

	public Produto find(Integer id) {
		
		Produto produto = produtoRepository.findOne(id);
		
		if(produto == null) {
			throw new ProdutoException("Produto não encontrado", HttpStatus.NOT_FOUND);
		}
		
		
		return produto;
	}
	
}
