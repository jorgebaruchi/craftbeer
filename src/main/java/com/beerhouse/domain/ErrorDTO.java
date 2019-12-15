package com.beerhouse.domain;

import lombok.Data;

@Data
public class ErrorDTO {
	
	private String error;		
	private String detail;	
	private Long status;	
	private Long timestamp;	
	
}
