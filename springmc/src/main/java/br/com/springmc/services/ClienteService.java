package br.com.springmc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springmc.domain.Cliente;
import br.com.springmc.repositories.ClienteRepository;
import br.com.springmc.services.exception.ClienteException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRespository;
	
	public Cliente buscaCliente(Integer idCliente) {
		Cliente cliente = clienteRespository.findOne(idCliente);
		
		if(cliente == null) {
			throw new ClienteException("Cliente não encontrada");
		}
		return cliente;
	}
}