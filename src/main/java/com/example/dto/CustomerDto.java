package com.example.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Customer;
import com.example.Flight;
import com.example.repository.FlightRepo;

public class CustomerDto {
	@Autowired
	FlightRepo flightRepo;

	private Long id;
//	@Size(45)
	private String name;
	private Collection<String> flightNames = new ArrayList<String>();;
	public static CustomerDto CreateFromEntity(Customer cust) {
		CustomerDto custDto = new CustomerDto();
		custDto.setName(cust.getName());
		custDto.setId(cust.getId());
		for(Flight flt: cust.getFlights()) {
			System.out.println("flight name in CreateFromEntity: " + flt.getName());
			custDto.flightNames.add(flt.getName());
		}
		return custDto;
	}
	
	public void CopyToEntity(Customer cust)
	{
		System.out.println("in CopyToEntity " );
		cust.setName(this.getName());
		for (String fltName : this.getFlightNames()) {
			System.out.println("flight name in CopyToEntity: " + fltName);
			Collection<Flight> custFlights = flightRepo.findByName(fltName);
			custFlights.forEach(flight -> cust.getFlights().add(flight));
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
	public Collection<String> getFlightNames() {
		return flightNames;
	}
	public void setFlightNames(Collection<String> flight_names) {
		this.flightNames = flight_names;
	}

	@Override
	public String toString() {
		return "CustomerDto [id=" + id + ", name=" + name + ", flightNames="
				+ flightNames + "]";
	}
	
}
