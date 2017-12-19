package br.com.springmc.services.exception;

import org.springframework.http.HttpStatus;

public class PedidoNegocioException extends RuntimeException {

private static final long serialVersionUID = 1L;

private HttpStatus httpStatus;
	
	public PedidoNegocioException(String msg) {
		super(msg);
	}
	
	public PedidoNegocioException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	
}
