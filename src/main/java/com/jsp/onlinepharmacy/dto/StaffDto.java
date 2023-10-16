package com.jsp.onlinepharmacy.dto;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class StaffDto {
	
	private int staffId;
	private String staffName;
	private String email;
	private long phoneNumber;
	
	@ManyToOne
	private AdminDto adminDto;
	@OneToOne
	private MedicalStoreDto medicalStoreDto;

	
	
}
