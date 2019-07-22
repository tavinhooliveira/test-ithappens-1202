package com.ithappens.service.exception;

public class ProductsStoqueException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductsStoqueException(String mensagem) {
		super(mensagem);
	}

	public ProductsStoqueException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
