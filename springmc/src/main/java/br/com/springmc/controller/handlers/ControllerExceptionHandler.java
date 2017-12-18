package br.com.springmc.controller.handlers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.springmc.services.exception.CategoriaException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(CategoriaException.class)
	public ResponseEntity<StandardError> categoriaNotFound(CategoriaException exception, HttpServletRequest request){
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(),exception.getMessage(),System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		
		
	}
}