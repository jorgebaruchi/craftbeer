package com.beerhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;

import com.beerhouse.business.BeerCategoryBusiness;
import com.beerhouse.domain.BeerCategory;
import com.beerhouse.exceptions.ServiceValidationException;
import com.beerhouse.validator.BeerCategoryValidator;

@Service
public class BeerCategoryService {

	@Autowired
	private BeerCategoryBusiness business;
	
	@Autowired
	private BeerCategoryValidator validator;
	
	public List<BeerCategory> list() {
		return business.list();
	}	
	
	public BeerCategory save(BeerCategory category) {
		validate(category);
		return business.save(category);
	}
	
	public BeerCategory find(Long id) {
		return business.find(id);
	}
	
	public void update(BeerCategory category) {
		validate(category);
		business.update(category);
	}
	
	public void delete(Long id) {
		business.delete(id);
	}
	
	private void validate(BeerCategory category) {
		BeanPropertyBindingResult validacao = new BeanPropertyBindingResult(category, "BeerCategory", false, Integer.MAX_VALUE);;
		validator.validate(category, validacao);
		if (validacao.hasErrors()) {
			throw new ServiceValidationException(validacao);
		}
	}
}