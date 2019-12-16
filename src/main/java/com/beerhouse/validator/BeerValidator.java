package com.beerhouse.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.beerhouse.domain.Beer;

@Service
public class BeerValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		if (clazz.isInstance(Beer.class))
			return true;
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Beer beer = (Beer) target;
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(beer.getBeerName());
        while (m.find()) {
        	errors.reject(m.group(), "Digito inválido para o nome");
        }
	}
	
}