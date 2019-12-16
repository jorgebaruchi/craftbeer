package com.beerhouse.exceptions;

public class BeerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1869300553614629710L;

	Long beerId;

	public BeerNotFoundException(String mensagem, Long beer) {
		super(mensagem);
		this.beerId = beer;
	}
	
	public BeerNotFoundException(String mensagem, Long beerId, Throwable causa) {
		super(mensagem, causa);
		this.beerId = beerId;
	}

	public Long getBeerId() {
		return this.beerId;
	}

}
