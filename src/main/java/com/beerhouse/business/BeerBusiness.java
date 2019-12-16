package com.beerhouse.business;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.beerhouse.domain.Beer;
import com.beerhouse.repository.BeerRepository;
import com.beerhouse.service.exceptions.BeerAlreadyExistsException;
import com.beerhouse.service.exceptions.BeerNotFoundException;

@Service
public class BeerBusiness {

	@Autowired
	private BeerRepository repository;
	
	public List<Beer> list() {
		return repository.findAll();
	}	
	
	public Beer save(Beer beer) {
		if(beer.getBeerId() != null) {
			Optional<Beer> beerAux = repository.findById(beer.getBeerId());
			
			if(beerAux.isPresent()) {
				throw new BeerAlreadyExistsException("A cerveja já existe.", beer);
			}
		}
		return repository.save(beer);
	}
	
	public Beer find(Long id) {
		Optional<Beer> beer = repository.findById(id);
		
		if(!beer.isPresent()) {
			throw new BeerNotFoundException("A cerveja não foi encontrada", id);
		}
		return beer.get();
	}
	
	public void update(Beer beer) {
		check(beer);
		repository.save(beer);
	}
	
	private void check(Beer beer) {
		find(beer.getBeerId());
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new BeerNotFoundException("A cerveja não foi encontrada.", id);
		}
	}
	
	public List<Beer> updatePrice(BigDecimal percent) {
		BigDecimal percentual = percent.divide(BigDecimal.valueOf(100L));
		List<Beer> beers = repository.findAll();
		beers.forEach(b -> b.setPrice(b.getPrice().multiply(percentual)));
		repository.saveAll(beers);
		return beers;
	}
}