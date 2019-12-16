package com.beerhouse.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.beerhouse.domain.BeerCategory;

@Service
public class BeerCategoryValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		if (clazz.isInstance(BeerCategory.class))
			return true;
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		BeerCategory category = (BeerCategory) target;
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(category.getCategoryName());
        while (m.find()) {
        	errors.reject(m.group(), "Digito inválido para o nome");
        }
	}
	
}
