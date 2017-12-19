package br.com.springmc.controller.handlers;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

	private static final long serialVersionUID = 1L;

	
	private List<FieldMessage> messagens = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Long time) {
		super(status, msg, time);
	}

	public List<FieldMessage> getErrors() {
		return messagens;
	}

	public void addError(String fieldName, String message) {
		this.messagens.add(new FieldMessage(fieldName, message));
	}
	
}
