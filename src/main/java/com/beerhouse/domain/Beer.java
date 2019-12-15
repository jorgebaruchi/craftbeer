package com.beerhouse.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity(name="beer")
@Data
public class Beer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long beerId;	
	
	@NotNull(message = "Campo beerName é de preencimento obrigatório.")
	@Size(max = 100, message = "Tamanho maximo excedido")
	private String beerName;	
	
	@Size(max = 1000, message = "Tamanho maximo excedido")
	private String ingredients;	
	
	@Size(max = 50, message = "Tamanho maximo excedido")
	private String alcoholContent;
	
	@NotNull(message = "Campo price é de preencimento obrigatório.")
	private BigDecimal price;
	
	@ManyToOne
	@NotNull(message = "Campo category é de preencimento obrigatório.")
	@JoinColumn(name = "CATEGORY_ID")
	private BeerCategory category;

}
