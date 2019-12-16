package com.beerhouse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController{
	
	@RequestMapping(path="/", method = RequestMethod.GET, produces = {MediaType.TEXT_HTML_VALUE})
	public ResponseEntity<String> test() {
		return ResponseEntity.status(HttpStatus.OK).body("Hello World!!!");
	}
}