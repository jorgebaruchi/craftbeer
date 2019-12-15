package com.beerhouse.controller;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.beerhouse.domain.Beer;
import com.beerhouse.service.BeerService;

@RestController
@RequestMapping("/beers")
public class BeerController{

	@Autowired
	private BeerService service;
	
	@RequestMapping(method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
	})
	@CrossOrigin
	public ResponseEntity<List<Beer>> list() {
		List<Beer> Beer = service.list();
		return ResponseEntity.status(HttpStatus.OK).body(Beer);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody Beer beer) {
		beer = service.save(beer);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(beer.getBeerId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Beer beer, 
			@PathVariable("id") Long id) {
		beer.setBeerId(id);
		service.update(beer);		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Beer> find(@PathVariable("id") Long id) {
		Beer beer = service.find(id);
		CacheControl cache = CacheControl.maxAge(10, TimeUnit.SECONDS);		
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cache).body(beer);
	}
}