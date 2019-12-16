package com.beerhouse.exceptions;

public class BeerCategoryNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1869300553614629710L;

	Long categoryId;

	public BeerCategoryNotFoundException(String mensagem, Long categoryId) {
		super(mensagem);
		this.categoryId = categoryId;
	}
	
	public BeerCategoryNotFoundException(String mensagem, Long category, Throwable causa) {
		super(mensagem, causa);
		this.categoryId = category;
	}

	public Long getCategoryId() {
		return this.categoryId;
	}
	
}
