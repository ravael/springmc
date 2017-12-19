package br.com.springmc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.Ssl.ClientAuth;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.springmc.domain.Cliente;
import br.com.springmc.dto.ClienteDTO;
import br.com.springmc.repositories.ClienteRepository;
import br.com.springmc.services.exception.ClienteNegocioException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRespository;
	
	public Cliente find(Integer idCliente) {
		Cliente cliente = clienteRespository.findOne(idCliente);
		
		if(cliente == null) {
			throw new ClienteNegocioException("Cliente não encontrada.");
		}
		return cliente;
	}
	
	public Cliente update(Cliente cliente) {
		Cliente resultado = find(cliente.getId());
		resultado.setEmail(cliente.getEmail());
		resultado.setNome(cliente.getNome());
		return clienteRespository.save(resultado);
	}

	public void delete(Integer id) {
		find(id);
		try {
			clienteRespository.delete(id);
		}catch (DataIntegrityViolationException e) {
			throw new ClienteNegocioException("Não é possível excluir uma Cliente que possui produtos.", HttpStatus.BAD_REQUEST);
		}
		
	}

	public List<Cliente> findAll() {
		return clienteRespository.findAll();
		
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRespository.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail());
	}
}
