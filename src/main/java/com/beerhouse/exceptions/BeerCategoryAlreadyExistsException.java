package com.beerhouse.exceptions;

import com.beerhouse.domain.BeerCategory;

public class BeerCategoryAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1869300553614629710L;

	BeerCategory category;

	public BeerCategoryAlreadyExistsException(String mensagem, BeerCategory category) {
		super(mensagem);
		this.category = category;
	}
	
	public BeerCategoryAlreadyExistsException(String mensagem, BeerCategory category, Throwable causa) {
		super(mensagem, causa);
		this.category = category;
	}
	
	public BeerCategory getBeerCategory() {
		return this.category;
	}
	
}
