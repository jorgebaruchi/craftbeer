package com.beerhouse.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.beerhouse.domain.BeerCategory;
import com.beerhouse.repository.BeerCategoryRepository;
import com.beerhouse.service.exceptions.BeerCategoryAlreadyExistsException;
import com.beerhouse.service.exceptions.BeerCategoryNotFoundException;

@Service
public class BeerCategoryBusiness {

	@Autowired
	private BeerCategoryRepository repository;
	
	public List<BeerCategory> list() {
		return repository.findAll();
	}	
	
	public BeerCategory save(BeerCategory category) {
		if(category.getCategoryId() != null) {
			Optional<com.beerhouse.domain.BeerCategory> categoryAux = repository.findById(category.getCategoryId());
			
			if(categoryAux.isPresent()) {
				throw new BeerCategoryAlreadyExistsException("A categoria já existe.", category);
			}
		}
		return repository.save(category);
	}
	
	public BeerCategory find(Long id) {
		Optional<BeerCategory> beerCategory = repository.findById(id);
		
		if(!beerCategory.isPresent()) {
			throw new BeerCategoryNotFoundException("A categoria não pôde ser encontrada.", id);
		}
		return beerCategory.get();
	}
	
	public void update(BeerCategory category) {
		check(category);
		repository.save(category);
	}
	
	private void check(BeerCategory category) {
		find(category.getCategoryId());
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new BeerCategoryNotFoundException("A categoria não pôde ser encontrada.", id);
		}
	}
}