package com.example.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Customer;
import com.example.Flight;
import com.example.repository.CustomerRepo;

public class FlightDto {
	@Autowired
	CustomerRepo customerRepo;

	private Long id;
//	@Size(45)
	private String name;
	private Collection<String> customerNames = new ArrayList<String>();
	public static FlightDto CreateFromEntity(Flight flight) {
		FlightDto flightDto = new FlightDto();
		flightDto.setName(flight.getName());
		flightDto.setId(flight.getId());
		for(Customer cust: flight.getCustomers()) {
			System.out.println("customer name in CreateFromEntity: " + cust.getName());
			flightDto.customerNames.add(cust.getName());
		}
		return flightDto;
	}
	
	public void CopyToEntity(Flight flight)
	{
		flight.setName(this.getName());
		for (String custName : this.getCustomerNames()) {
			Collection<Customer> flightCustomers = customerRepo.findByName(custName);
			flightCustomers.forEach(customer -> flight.getCustomers().add(customer));
		}
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Collection<String> getCustomerNames() {
		return customerNames;
	}
	public void setCustomerNames(Collection<String> customer_names) {
		this.customerNames = customer_names;
	}

}
