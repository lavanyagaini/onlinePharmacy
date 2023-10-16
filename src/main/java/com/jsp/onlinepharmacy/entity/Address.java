package com.jsp.onlinepharmacy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;


@Entity
@Data
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int addressId;
	private String streetName;
	private String city;
	private String state;
	private long pincode;
	
	@ManyToOne
    @JoinColumn
    @JsonIgnore
    private Customer customer;
    
    
    @OneToOne
    @JoinColumn
    private MedicalStore medicalStore;
    
	

}
