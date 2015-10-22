package com.example;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Flight {
	public Flight() {
	}
	public Flight(String flightName) {
		this.name = flightName;
		// this.customers = new ArrayList<Customer>();
	}
	@Id @GeneratedValue
	private Long id;
	private String name;
	@ManyToMany
    @JoinTable(name = "customer_flights", joinColumns = @JoinColumn(name = "flight_id", referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id") )
    @JsonManagedReference
	private Collection<Customer> customers;
	@Override
	public String toString() {
		return "Flight [flightName=" + name + "]";
	}
	public String getName() {
		return name;
	}
	public Collection<Customer> getCustomers() {
		return customers;
	}
}
