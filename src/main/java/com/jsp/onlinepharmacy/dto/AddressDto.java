package com.jsp.onlinepharmacy.dto;

import lombok.Data;

@Data
public class AddressDto {
	
	private int addressId;
	private String streetName;
	private String city;
	private String state;
	private long pincode;
	
	
}
