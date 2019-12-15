package com.beerhouse.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Entity(name="beerCategory")
@Data
public class BeerCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;
	
	@NotNull(message = "Campo categoryName é de preencimento obrigatório.")
	@Size(max = 200, message = "Tamanho maximo excedido")
	@JsonInclude(Include.NON_NULL)
	private String categoryName;
	
	@NotNull(message = "Campo paisOrigem é de preencimento obrigatório.")
	@Size(max = 80, message = "Tamanho maximo excedido")
	@JsonInclude(Include.NON_NULL)
	private String paisOrigem;	

}
