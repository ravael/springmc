package br.com.springmc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.springmc.domain.Cidade;
import br.com.springmc.domain.Cliente;
import br.com.springmc.domain.Endereco;
import br.com.springmc.domain.enums.TipoCliente;
import br.com.springmc.dto.ClienteDTO;
import br.com.springmc.dto.ClienteNewDTO;
import br.com.springmc.repositories.CidadeRepository;
import br.com.springmc.repositories.ClienteRepository;
import br.com.springmc.repositories.EnderecoRepository;
import br.com.springmc.services.exception.ClienteNegocioException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRespository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
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

	public Cliente fromDTO(ClienteNewDTO dto) {
		Cliente cliente = new  Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpfCnpj(), TipoCliente.toEnum(dto.getTipo()));
		Cidade cidade = cidadeRepository.findOne(dto.getCidadeId());
		Endereco endereco = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(), dto.getCep(), cliente, cidade);
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(dto.getTelefone1());
		
		if(dto.getTelefone2() != null) {
			cliente.getTelefones().add(dto.getTelefone2());
		}
		if(dto.getTelefone3() != null) {
			cliente.getTelefones().add(dto.getTelefone3());
		}

		return cliente;
	}

	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		Cliente resultado = clienteRespository.save(cliente);
		enderecoRepository.save(cliente.getEnderecos());
		return resultado;
	}
}
