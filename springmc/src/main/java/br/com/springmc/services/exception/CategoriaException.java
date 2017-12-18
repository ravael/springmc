package br.com.springmc.services.exception;

public class CategoriaException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public CategoriaException(String msg) {
		super(msg);
	}
	
	public CategoriaException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
