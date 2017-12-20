package br.com.springmc;

import java.text.SimpleDateFormat;
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
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama, mesa e banho");
		Categoria cat4 = new Categoria(null, "Perfumaria");
		Categoria cat5 = new Categoria(null, "Musica");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Jardinagem");
		
		Produto produto1 = new Produto(null, "Computador", 200.0);
		Produto produto2 = new Produto(null, "Impressora", 2000.0);
		Produto produto3 = new Produto(null, "Mouse", 80.0);
		
		cat1.getProdutos().addAll(Arrays.asList(produto1,produto2,produto3));
		cat2.getProdutos().addAll(Arrays.asList(produto2));
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
  		Produto p2 = new Produto(null, "Impressora", 800.00);
  		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV true color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
				
		categoriaRepository.save(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.save(Arrays.asList(p1, p2, p3));
		produtoRepository.save(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		

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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("12/02/1991"), sdf.parse("12/02/1991"));
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
