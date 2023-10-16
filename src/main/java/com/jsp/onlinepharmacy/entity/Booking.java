package com.jsp.onlinepharmacy.entity;

import java.time.LocalDate;
import java.util.List;

import com.jsp.onlinepharmacy.enums.BookingStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Booking {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int bookingId;
	private LocalDate orderDate;
	private int quantity;
	private String payMentMode;
	private LocalDate expectedDate;
	private BookingStatus bookingStatus;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	List<Medicine> medicines;
	@ManyToOne
	@JoinColumn
	private Customer customer;
	

	
}
