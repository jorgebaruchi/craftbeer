package com.beerhouse.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.beerhouse.domain.ErrorDTO;
import com.beerhouse.service.exceptions.BeerAlreadyExistsException;
import com.beerhouse.service.exceptions.BeerCategoryAlreadyExistsException;
import com.beerhouse.service.exceptions.BeerCategoryNotFoundException;
import com.beerhouse.service.exceptions.BeerNotFoundException;
import com.beerhouse.service.exceptions.ServiceValidationException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ServiceValidationException.class)
	public ResponseEntity<ErrorDTO> handleServiceValidationException
							(ServiceValidationException e, HttpServletRequest request) {
		
		ErrorDTO erro = new ErrorDTO();
		erro.setStatus(400l);
		erro.setError(e.getMessage());
		erro.setDetail("Corrija os parâmetros e repita a operação");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(BeerNotFoundException.class)
	public ResponseEntity<ErrorDTO> handleBeerNotFoundException
							(BeerNotFoundException e, HttpServletRequest request) {
		
		ErrorDTO erro = new ErrorDTO();
		erro.setStatus(404l);
		erro.setError(e.getMessage());
		erro.setDetail(e.getBeerId().toString());
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(BeerAlreadyExistsException.class)
	public ResponseEntity<ErrorDTO> handleBeerAlreadyExistsException
							(BeerAlreadyExistsException e, HttpServletRequest request) {
		
		ErrorDTO erro = new ErrorDTO();
		erro.setStatus(409l);
		erro.setError(e.getMessage());
		erro.setDetail(e.getBeer().toString());
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
	
	@ExceptionHandler(BeerCategoryNotFoundException.class)
	public ResponseEntity<ErrorDTO> handleBeerCategoryNotFoundException
							(BeerCategoryNotFoundException e, HttpServletRequest request) {
		
		ErrorDTO erro = new ErrorDTO();
		erro.setStatus(404l);
		erro.setError(e.getMessage());
		erro.setDetail(e.getCategoryId().toString());
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(BeerCategoryAlreadyExistsException.class)
	public ResponseEntity<ErrorDTO> handleBeerCategoryAlreadyExistsException
							(BeerCategoryAlreadyExistsException e, HttpServletRequest request) {
		
		ErrorDTO erro = new ErrorDTO();
		erro.setStatus(409l);
		erro.setError(e.getMessage());
		erro.setDetail(e.getBeerCategory().toString());
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorDTO> handleDataIntegrityViolationException
							(DataIntegrityViolationException e, HttpServletRequest request) {
		
		ErrorDTO erro = new ErrorDTO();
		erro.setStatus(400l);
		erro.setError(e.getMessage());
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
}