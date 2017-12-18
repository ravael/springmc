package br.com.springmc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.springmc.domain.Categoria;
import br.com.springmc.domain.Cidade;
import br.com.springmc.domain.Estado;
import br.com.springmc.domain.Produto;
import br.com.springmc.repositories.CategoriaRepository;
import br.com.springmc.repositories.CidadeRepository;
import br.com.springmc.repositories.EstadoRepository;
import br.com.springmc.repositories.ProdutoRepository;

@SpringBootApplication
public class SpringmcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringmcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Categoria categoria1 = new Categoria(null, "Informática");
		Categoria categoria2 = new Categoria(null, "Escritório");
		
		Produto produto1 = new Produto(null, "Computador", 200.0);
		Produto produto2 = new Produto(null, "Impressora", 2000.0);
		Produto produto3 = new Produto(null, "Mouse", 80.0);
		
		categoria1.getProdutos().addAll(Arrays.asList(produto1,produto2,produto3));
		categoria2.getProdutos().addAll(Arrays.asList(produto2));
		
		produto1.getCategorias().addAll(Arrays.asList(categoria1));
		produto2.getCategorias().addAll(Arrays.asList(categoria1,categoria2));
		produto3.getCategorias().addAll(Arrays.asList(categoria1));
		
		
		categoriaRepository.save(Arrays.asList(categoria1,categoria2));
		produtoRepository.save(Arrays.asList(produto1,produto2,produto3));
		

		Estado estado1 = new Estado(null, "Minas Gerais");
		Estado estado2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", estado1);
		Cidade c2 = new Cidade(null, "São Paulo", estado2);
		Cidade c3 = new Cidade(null, "Campinas", estado1);
		
		estado1.getCidades().addAll(Arrays.asList(c1));
		estado2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.save(Arrays.asList(estado1, estado2));
		cidadeRepository.save(Arrays.asList(c1,c2,c3));
	}
}
