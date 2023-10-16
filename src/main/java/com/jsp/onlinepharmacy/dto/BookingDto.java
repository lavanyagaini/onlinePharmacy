package com.jsp.onlinepharmacy.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BookingDto {
	
	private int bookingId;
	private LocalDate orderDate;
	private int quantity;
	private String payMentMode;
	private LocalDate expectedDate;
	
}
