package com.jsp.onlinepharmacy.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MedicineDto {

	
	private int medicineId;
	private String medicineName;
	private double cost;
	private LocalDate expiryDate;
	private int stockQuantity;
	private String manufacturer;
	private String description;
	
	
}
