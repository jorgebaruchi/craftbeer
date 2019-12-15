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

import com.beerhouse.domain.BeerCategory;
import com.beerhouse.service.BeerCategoryService;

@RestController
@RequestMapping("/category")
public class BeerCategoryController {

	@Autowired
	private BeerCategoryService service;
	
	@RequestMapping(method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
	})
	@CrossOrigin
	public ResponseEntity<List<BeerCategory>> list() {
		List<BeerCategory> category = service.list();
		return ResponseEntity.status(HttpStatus.OK).body(category);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody BeerCategory category) {
		category = service.save(category);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(category.getCategoryId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody BeerCategory category, 
			@PathVariable("id") Long id) {
		category.setCategoryId(id);
		service.update(category);		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<BeerCategory> find(@PathVariable("id") Long id) {
		BeerCategory category = service.find(id);
		CacheControl cache = CacheControl.maxAge(30, TimeUnit.SECONDS);		
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cache).body(category);
	}
}