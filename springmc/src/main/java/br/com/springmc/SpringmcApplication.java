package br.com.springmc;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.springmc.domain.Categoria;
import br.com.springmc.domain.Cidade;
import br.com.springmc.domain.Cliente;
import br.com.springmc.domain.Endereco;
import br.com.springmc.domain.Estado;
import br.com.springmc.domain.ItemPedido;
import br.com.springmc.domain.Pagamento;
import br.com.springmc.domain.PagamentoComBoleto;
import br.com.springmc.domain.PagamentoComCartao;
import br.com.springmc.domain.Pedido;
import br.com.springmc.domain.Produto;
import br.com.springmc.domain.enums.EstadoPagamento;
import br.com.springmc.domain.enums.TipoCliente;
import br.com.springmc.repositories.CategoriaRepository;
import br.com.springmc.repositories.CidadeRepository;
import br.com.springmc.repositories.ClienteRepository;
import br.com.springmc.repositories.EnderecoRepository;
import br.com.springmc.repositories.EstadoRepository;
import br.com.springmc.repositories.ItemPedidoRepository;
import br.com.springmc.repositories.PagamentoRepository;
import br.com.springmc.repositories.PedidoRepository;
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
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

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
		
		Cliente cli = new Cliente(null, "Maria", "Maria@Maria", "12345", TipoCliente.PESSOAFISICA);
		cli.getTelefones().addAll(Arrays.asList("321-321","123-456"));
		
		Endereco e1 = new Endereco(null, "Sudoeste", "10", "quadra 3", "sudoeste", "123", cli, c1);
		Endereco e2 = new Endereco(null, "Asa Sul", "50", "quadra 307", "asasul", "123", cli, c2);
		
		cli.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.save(Arrays.asList(cli));
		enderecoRepository.save(Arrays.asList(e1,e2));
		
		Pedido ped1 = new Pedido(null, LocalDate.now(), cli, e1);
		Pedido ped2 = new Pedido(null, LocalDate.now(), cli, e2);
		
		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);
		
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, LocalDate.now(), LocalDate.now());
		ped2.setPagamento(pag2);
		
		cli.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.save(Arrays.asList(ped1,ped2));
		pagamentoRepository.save(Arrays.asList(pag1,pag2));
		
		ItemPedido ip1 = new ItemPedido(ped1, produto1, 0.0, 1, 2000.0);
		ItemPedido ip2 = new ItemPedido(ped1, produto3, 0.0, 2, 80.0);
		ItemPedido ip3 = new ItemPedido(ped2, produto2, 100.0, 1, 800.0);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		produto1.getItens().addAll(Arrays.asList(ip1));
		produto2.getItens().addAll(Arrays.asList(ip3));
		produto3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.save(Arrays.asList(ip1,ip2,ip3));
		
	}
}
