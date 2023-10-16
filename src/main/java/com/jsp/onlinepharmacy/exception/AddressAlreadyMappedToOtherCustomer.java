package com.jsp.onlinepharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressAlreadyMappedToOtherCustomer extends RuntimeException{
	
	private String message;

}
