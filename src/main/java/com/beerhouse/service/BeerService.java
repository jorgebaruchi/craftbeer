package com.beerhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;

import com.beerhouse.business.BeerBusiness;
import com.beerhouse.domain.Beer;
import com.beerhouse.service.exceptions.ServiceValidationException;
import com.beerhouse.validator.BeerValidator;

@Service
public class BeerService {

	@Autowired
	private BeerBusiness business;
	
	@Autowired
	private BeerValidator validator;
	
	public List<Beer> list() {
		return business.list();
	}	
	
	@SuppressWarnings("null")
	public Beer save(Beer beer) {
		validate(beer);
		return business.save(beer);
	}
	
	public Beer find(Long id) {
		return business.find(id);
	}
	
	public void update(Beer beer) {
		validate(beer);
		business.update(beer);
	}
	
	public void delete(Long id) {
		business.delete(id);
	}

	private void validate(Beer beer) {
		BeanPropertyBindingResult validacao = new BeanPropertyBindingResult(beer, "Beer", false, Integer.MAX_VALUE);;
		validator.validate(beer, validacao);
		if (validacao.hasErrors()) {
			throw new ServiceValidationException(validacao);
		}
	}
}