package com.jsp.onlinepharmacy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class MedicalStore {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int storeId;
	private String name;
	private String managerName;
	private long phone;
	
	@ManyToOne
	@JsonIgnore
	private Admin admin;
	
	@OneToOne(mappedBy = "medicalStore")
	private Address address;

	

}
