package br.com.springmc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springmc.domain.Pedido;
import br.com.springmc.repositories.PedidoRepository;
import br.com.springmc.services.exception.PedidoException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido buscaPedido(Integer idPedido) {
		Pedido pedido = pedidoRepository.findOne(idPedido);
		
		if(pedido == null) {
			throw new PedidoException("Pedido não encontrada");
		}
		return pedido;
	}
}
