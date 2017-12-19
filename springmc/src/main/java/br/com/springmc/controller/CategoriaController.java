package br.com.springmc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.springmc.domain.Categoria;
import br.com.springmc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Categoria categoria = categoriaService.buscaCategoria(id);
		
		return ResponseEntity.ok().body(categoria);
	}
}
