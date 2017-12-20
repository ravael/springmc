package br.com.springmc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.springmc.controller.handlers.FieldMessage;
import br.com.springmc.domain.Cliente;
import br.com.springmc.dto.ClienteDTO;
import br.com.springmc.repositories.ClienteRepository;

public class ClienteValidadorUpdate implements ConstraintValidator<ClienteValidationInsertCustom, ClienteDTO>{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void initialize(ClienteValidationInsertCustom arg0) {
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isValid(ClienteDTO dto, ConstraintValidatorContext context) {
		
		Map<String,String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer id = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> messages = new ArrayList<>();
		
		Cliente aux = clienteRepository.findByEmail(dto.getEmail());
		if(aux != null && !aux.getId().equals(id)) {
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
