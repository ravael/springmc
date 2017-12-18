package br.com.springmc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springmc.domain.Categoria;
import br.com.springmc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRespository;
	
	public Categoria listaCategorio(Integer idCategoria) {
		return categoriaRespository.findOne(idCategoria);
	}
}