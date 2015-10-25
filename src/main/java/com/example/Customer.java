package com.example;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.dto.CustomerDto;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Customer {
	public static final int MAX_STRING_SIZE = 45;
	public Customer() {
	}
	public Customer(String name) {
		this.name = name;
		// this.flights = new ArrayList<Flight>();
	}
	@Id @GeneratedValue
	private Long id;
	private String name;
    @JoinTable(name = "customer_flights", joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "flight_id", referencedColumnName = "id") )
	@ManyToMany	// (fetch = FetchType.EAGER)
    // @JsonBackReference
	private Collection<Flight> flights;
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Collection<Flight> getFlights() {
		return flights;
	}
	@Override
	public String toString() {
		return "Customer [customerName=" + name + "]";
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
