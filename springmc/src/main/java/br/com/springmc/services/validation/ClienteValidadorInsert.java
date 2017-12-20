package br.com.springmc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.springmc.controller.handlers.FieldMessage;
import br.com.springmc.domain.Cliente;
import br.com.springmc.domain.enums.TipoCliente;
import br.com.springmc.dto.ClienteNewDTO;
import br.com.springmc.repositories.ClienteRepository;
import br.com.springmc.services.validation.utils.BR;

public class ClienteValidadorInsert implements ConstraintValidator<ClienteValidationInsertCustom, ClienteNewDTO>{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteValidationInsertCustom arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(ClienteNewDTO dto, ConstraintValidatorContext context) {
		List<FieldMessage> messages = new ArrayList<>();
		
		if(dto.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !BR.isValidCPF(dto.getCpfCnpj())) {
			messages.add(new FieldMessage("cpfCnpj", "CPF inválido"));
		}
		
		if(dto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !BR.isValidCNPJ(dto.getCpfCnpj())) {
			messages.add(new FieldMessage("cpfCnpj", "CNPJ inválido"));
		}
		
		Cliente aux = clienteRepository.findByEmail(dto.getEmail());
		if(aux != null) {
			messages.add(new FieldMessage("Email", "Email já cadastrado"));
		}
		
		for (FieldMessage fieldMessage : messages) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage()).addPropertyNode(fieldMessage.getFieldName())
				.addConstraintViolation();
		}
		return messages.isEmpty();
	}

}
