package br.com.springmc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springmc.domain.Categoria;
import br.com.springmc.repositories.CategoriaRepository;
import br.com.springmc.services.exception.CategoriaException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRespository;
	
	public Categoria listaCategorio(Integer idCategoria) {
		Categoria categoria = categoriaRespository.findOne(idCategoria);
		
		if(categoria == null) {
			throw new CategoriaException("Categoria não encontrada");
		}
		return categoriaRespository.findOne(idCategoria);
	}
}
