package br.com.springmc.controller.handlers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.springmc.services.exception.CategoriaNegocioException;
import br.com.springmc.services.exception.ClienteNegocioException;
import br.com.springmc.services.exception.PedidoNegocioException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(CategoriaNegocioException.class)
	public ResponseEntity<StandardError> categoriaNotFound(CategoriaNegocioException exception, HttpServletRequest request){
		
		StandardError err = new StandardError(exception.getHttpStatus().value() ,exception.getMessage(),System.currentTimeMillis());
		
		
		return ResponseEntity.status(exception.getHttpStatus()).body(err);
		
	}
	
	@ExceptionHandler(ClienteNegocioException.class)
	public ResponseEntity<StandardError> clienteNotFound(ClienteNegocioException exception, HttpServletRequest request){
		
		StandardError err = new StandardError(exception.getHttpStatus().value(),exception.getMessage(),System.currentTimeMillis());
		
		return ResponseEntity.status(exception.getHttpStatus()).body(err);
		
	}
	
	@ExceptionHandler(PedidoNegocioException.class)
	public ResponseEntity<StandardError> pedidoNotFound(PedidoNegocioException exception, HttpServletRequest request){
		
		StandardError err = new StandardError(exception.getHttpStatus().value(),exception.getMessage(),System.currentTimeMillis());
		
		return ResponseEntity.status(exception.getHttpStatus()).body(err);
		
	}
	
}
