package com.jsp.onlinepharmacy.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int customerId;
	private String customerName;
	private String email;
	private String password;
	private long phoneNumber;
	
	@OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE)
	private List<Address> addresses;
	@OneToMany(mappedBy = "customer")
	@JsonIgnore
	private List<Booking> bookings;
	
	
	
}
