package com.jsp.onlinepharmacy.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacy.Dao.AddressDao;
import com.jsp.onlinepharmacy.Dao.CustomerDao;
import com.jsp.onlinepharmacy.dto.AddressDto;
import com.jsp.onlinepharmacy.dto.BookingDto;
import com.jsp.onlinepharmacy.dto.CustomerDto;
import com.jsp.onlinepharmacy.entity.Address;
import com.jsp.onlinepharmacy.entity.Booking;
import com.jsp.onlinepharmacy.entity.Customer;
import com.jsp.onlinepharmacy.exception.AddressIdNotFoundException;
import com.jsp.onlinepharmacy.exception.CustomerIdNotFoundException;
import com.jsp.onlinepharmacy.exception.EmailNotFoundException;
import com.jsp.onlinepharmacy.exception.InvalidPasswordException;
import com.jsp.onlinepharmacy.exception.PhoneNumberNotValidException;
import com.jsp.onlinepharmacy.util.ResponseStructure;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<CustomerDto>> saveCustomer(int addressId, Customer customer) {
		Address dbAddress=addressDao.findAddressById(addressId);
//		checking addressId is present or not
		if(dbAddress!=null) {
//			yes addressId is present
			List<Address> addresses=new ArrayList<Address>();
			addresses.add(dbAddress);
//			customer=only the own attributes not a relationship attributes
			customer.setAddresses(addresses);
//			now customer is having adddress also
			Customer dbCustomer=customerDao.saveCustomer(customer);
//	dbCustomer is having its own attributes then relationship attributes that is List address and List of bookings but its null
//			but list  of address is present
//			Copy Customer to customerDto
//			but this customer dto is having ListofaddresssesDto and ListOfBooking dto but still it is null;
//			copy List of Addressto addressDto
			CustomerDto customerDto=this.modelMapper.map(dbCustomer, CustomerDto.class);
			List<AddressDto> addressDtos;
		for(Address address:dbCustomer.getAddresses()) {
			AddressDto addressDto=this.modelMapper.map(address, AddressDto.class);
			addressDtos=new ArrayList<AddressDto>();
			addressDtos.add(addressDto);
			customerDto.setAddresses(addressDtos);
		} 
			customerDto.setBookingDtos(null);
			ResponseStructure<CustomerDto> structure=new ResponseStructure<CustomerDto>();
			structure.setMessage("CustometrAddedsuccessfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(customerDto);
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.CREATED);
			
		}else {
			throw new AddressIdNotFoundException("Sorry failed to add customer");
		}
	}
	
	public ResponseEntity<ResponseStructure<CustomerDto>> updateCustomer(int customerId, Customer customer) {
		Customer dbCustomer=customerDao.updateCustomer(customerId,customer);
		if(dbCustomer!=null) {
			CustomerDto customerDto=this.modelMapper.map(dbCustomer, CustomerDto.class);
			List<AddressDto> addressDtos;
		for(Address address:dbCustomer.getAddresses()) {
			AddressDto addressDto=this.modelMapper.map(address, AddressDto.class);
			addressDtos=new ArrayList<AddressDto>();
			addressDtos.add(addressDto);
			customerDto.setAddresses(addressDtos);
		} 
			customerDto.setBookingDtos(null);
			ResponseStructure<CustomerDto> structure=new ResponseStructure<CustomerDto>();
			structure.setMessage("CustometrAddedsuccessfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(customerDto);
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.CREATED);
		}else {
			throw new CustomerIdNotFoundException("Sorry failed to update Customer");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> getCustomerById(int customerId) {
	Customer dbCustomer=customerDao.getCustomerById(customerId);
	if(dbCustomer!=null) {
		CustomerDto customerDto=this.modelMapper.map(dbCustomer, CustomerDto.class);
		List<AddressDto> addressDtos;
	for(Address address:dbCustomer.getAddresses()) {
		AddressDto addressDto=this.modelMapper.map(address, AddressDto.class);
		addressDtos=new ArrayList<AddressDto>();
		addressDtos.add(addressDto);
		customerDto.setAddresses(addressDtos);
	} 
		customerDto.setBookingDtos(null);
		ResponseStructure<CustomerDto> structure=new ResponseStructure<CustomerDto>();
		structure.setMessage("Customer data fetched successfully");
		structure.setStatus(HttpStatus.FOUND.value());
		structure.setData(customerDto);
		return new ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.FOUND);
	}else {
		throw new CustomerIdNotFoundException("Sorry failed to fetch Customer");
	}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> deleteCustomerById(int customerId) {
		Customer dbCustomer=customerDao.deleteCustomerById(customerId);
		if(dbCustomer!=null) {
			CustomerDto customerDto=this.modelMapper.map(dbCustomer, CustomerDto.class);
			List<AddressDto> addressDtos;
		for(Address address:dbCustomer.getAddresses()) {
			AddressDto addressDto=this.modelMapper.map(address, AddressDto.class);
			addressDtos=new ArrayList<AddressDto>();
			addressDtos.add(addressDto);
			customerDto.setAddresses(addressDtos);
		} 
			customerDto.setBookingDtos(null);
			ResponseStructure<CustomerDto> structure=new ResponseStructure<CustomerDto>();
			structure.setMessage("Customer data deleted successfully");
			structure.setStatus(HttpStatus.GONE.value());
			structure.setData(customerDto);
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.GONE);
		}else {
			throw new CustomerIdNotFoundException("Sorry failed to delete Customer");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> loginCustomer(String email, String password) {
		Customer dbCustomer=customerDao.findByEmail(email);
		if(dbCustomer!=null) {
			if(dbCustomer.getPassword().equals(password)) {
//				login success
				CustomerDto customerDto=this.modelMapper.map(dbCustomer, CustomerDto.class);
				List<AddressDto> addressDtos;
			for(Address address:dbCustomer.getAddresses()) {
				AddressDto addressDto=this.modelMapper.map(address, AddressDto.class);
				addressDtos=new ArrayList<AddressDto>();
				addressDtos.add(addressDto);
				customerDto.setAddresses(addressDtos);
			} 
			List<BookingDto> bookingDtos;
			for(Booking booking:dbCustomer.getBookings()) {
				BookingDto bookingDto=this.modelMapper.map(booking, BookingDto.class);
				bookingDtos=new ArrayList<BookingDto>();
				bookingDtos.add(bookingDto);
				customerDto.setBookingDtos(bookingDtos);
			}
				
				ResponseStructure<CustomerDto> structure=new ResponseStructure<CustomerDto>();
				structure.setMessage("Customer Login Success WELCOME TO ONLINEPHARMACY");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(customerDto);
				return new ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.OK);
				
			}else {
//				invalid password
				throw new InvalidPasswordException("Sorry failed to Login");
			}
			
			
		}else {
			throw new EmailNotFoundException("Sorry Failed to Login");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> forgotPassword(String email, long phone,String password) {
	   Customer customer=customerDao.findByEmail(email);
		if(customer!=null) {
//			customer is present
			if(customer.getPhoneNumber()==phone) {
//				phone is registered
				customer.setPassword(password);
				Customer dbCustomer=customerDao.updateCustomer(customer.getCustomerId(), customer);
				
				
				CustomerDto customerDto=this.modelMapper.map(dbCustomer, CustomerDto.class);
				List<AddressDto> addressDtos;
			for(Address address:dbCustomer.getAddresses()) {
				AddressDto addressDto=this.modelMapper.map(address, AddressDto.class);
				addressDtos=new ArrayList<AddressDto>();
				addressDtos.add(addressDto);
				customerDto.setAddresses(addressDtos);
			} 
			List<BookingDto> bookingDtos;
			for(Booking booking:dbCustomer.getBookings()) {
				BookingDto bookingDto=this.modelMapper.map(booking, BookingDto.class);
				bookingDtos=new ArrayList<BookingDto>();
				bookingDtos.add(bookingDto);
				customerDto.setBookingDtos(bookingDtos);
			}
				
				ResponseStructure<CustomerDto> structure=new ResponseStructure<CustomerDto>();
				structure.setMessage("Customer Pssword reset successfully");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(customerDto);
				return new ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.OK);
				
				
			}else {
				throw new PhoneNumberNotValidException("Sorry failed to reset password Please enter Registered PhoneNumber");
			}
		}else {
			throw new EmailNotFoundException("Sorry failed to reset password Please enter valid Email");
		}
	}
}
