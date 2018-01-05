package br.com.springmc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springmc.domain.ItemPedido;
import br.com.springmc.domain.PagamentoComBoleto;
import br.com.springmc.domain.Pedido;
import br.com.springmc.domain.enums.EstadoPagamento;
import br.com.springmc.repositories.ItemPedidoRepository;
import br.com.springmc.repositories.PagamentoRepository;
import br.com.springmc.repositories.PedidoRepository;
import br.com.springmc.repositories.ProdutoRepository;
import br.com.springmc.services.exception.PedidoNegocioException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public Pedido find(Integer idPedido) {
		Pedido pedido = pedidoRepository.findOne(idPedido);

		if (pedido == null) {
			throw new PedidoNegocioException("Pedido não encontrada.");
		}
		return pedido;
	}

	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoRepository.findOne(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.save(obj.getItens());
		return obj;
	}
}
