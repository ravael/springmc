package br.com.springmc.services.exception;

import org.springframework.http.HttpStatus;

public class ClienteNegocioException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private HttpStatus httpStatus;
	
	public ClienteNegocioException(String msg) {
		super(msg);
	}
	
	public ClienteNegocioException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ClienteNegocioException(String msg, HttpStatus httpStatus) {
		super(msg);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	

}
