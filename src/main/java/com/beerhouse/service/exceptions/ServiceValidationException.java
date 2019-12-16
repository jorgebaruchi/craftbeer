package com.beerhouse.service.exceptions;

import org.springframework.validation.BindingResult;

public class ServiceValidationException extends RuntimeException {

	private static final long serialVersionUID = 1869300553614629710L;

	private final BindingResult bindingResult;

	public ServiceValidationException(BindingResult bindingResult) {
		super(String.format("Erro(s) de validação do component %s: %s", bindingResult.getObjectName(), bindingResult.getAllErrors()));
		this.bindingResult = bindingResult;
	}

	public BindingResult getBindingResult() {
		return bindingResult;
	}
	
}
