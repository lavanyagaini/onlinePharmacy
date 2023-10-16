package com.jsp.onlinepharmacy.dto;

import com.jsp.onlinepharmacy.entity.Address;
import com.jsp.onlinepharmacy.entity.Admin;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class MedicalStoreDto {
	
	private int storeId;
	private String name;
	private String managerName;
	private long phone;
	
	@ManyToOne
	private AdminDto adminDto;
	
	@OneToOne
	private AddressDto addressDto; 

	
	
}
