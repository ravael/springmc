package br.com.springmc.services.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class ProdutoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Autowired
	private HttpStatus httpStatus;
	
	
	public ProdutoException(String msg) {
		super(msg);
	}
	
	public ProdutoException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
	public ProdutoException(String msg, HttpStatus status) {
		super(msg);
		this.httpStatus = status;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	

}
