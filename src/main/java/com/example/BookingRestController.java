package com.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CustomerDto;
import com.example.dto.FlightDto;

@RestController
public class BookingRestController {
	@Autowired 
	private CustomerRepo customerRepo;
	@RequestMapping("/customers")
	Collection<CustomerDto> Bookings() {
		Collection<CustomerDto> customerDtos = new ArrayList<CustomerDto>();
		Collection<Customer> customers = this.customerRepo.findAll();
		for (Customer customer: customers) {
			customerDtos.add(CustomerDto.CreateFromEntity(customer));
		}
		System.out.println("customers size: " + customerDtos.size());
		return customerDtos;
	}
	
	@Autowired 
	private FlightRepo flightRepo;
	@RequestMapping("/flights")
	Collection<FlightDto> Flights() {
		Collection<FlightDto> flightDtos = new ArrayList<FlightDto>();
		Collection<Flight> flights = this.flightRepo.findAll();
		for (Flight flight: flights) {
			flightDtos.add(FlightDto.CreateFromEntity(flight));
		}
		System.out.println("flights size: " + flightDtos.size());
		return flightDtos;
	}
}
