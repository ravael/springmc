package br.com.springmc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.springmc.controller.handlers.FieldMessage;
import br.com.springmc.domain.enums.TipoCliente;
import br.com.springmc.dto.ClienteNewDTO;
import br.com.springmc.services.validation.utils.BR;

public class ClienteValidador implements ConstraintValidator<ClienteValidationCustom, ClienteNewDTO>{

	@Override
	public void initialize(ClienteValidationCustom arg0) {
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
		
		for (FieldMessage fieldMessage : messages) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage()).addPropertyNode(fieldMessage.getFieldName())
				.addConstraintViolation();
		}
		return messages.isEmpty();
	}

}
