package com.example;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingRestController {
	@Autowired 
	private CustomerRepo CustomerRepo;
	@RequestMapping("/customers")
	Collection<Customer> Bookings() {
		Collection<Customer> customers = this.CustomerRepo.findAll();
		System.out.println("customers size: " + customers.size());
		return customers;
	}
	
	@Autowired 
	private FlightRepo flightRepo;
	@RequestMapping("/flights")
	Collection<Flight> Flights() {
		Collection<Flight> flights = this.flightRepo.findAll();
		System.out.println("flights size: " + flights.size());
		return flights;
	}
}
