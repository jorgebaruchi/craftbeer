package com.beerhouse.exceptions;

import com.beerhouse.domain.Beer;

public class BeerAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1869300553614629710L;
	
	Beer beer;

	public BeerAlreadyExistsException(String mensagem, Beer beer) {
		super(mensagem);
		this.beer = beer;
	}
	
	public BeerAlreadyExistsException(String mensagem, Beer beer, Throwable causa) {
		super(mensagem, causa);
		this.beer = beer;
	}
	
	public Beer getBeer() {
		return this.beer;
	}	
	
}
