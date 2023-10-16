package com.jsp.onlinepharmacy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Staff {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int staffId;
	private String staffName;
	private String email;
	private String password;
	private long phoneNumber;
	
	@ManyToOne
	private Admin admin;
	
	@OneToOne
	private MedicalStore medicalStore;

	
	
}
