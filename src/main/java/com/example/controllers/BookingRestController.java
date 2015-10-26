package com.example.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Flight;
import com.example.dto.CustomerDto;
import com.example.dto.FlightDto;
import com.example.repository.FlightRepo;

@RestController
public class BookingRestController {
	
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
