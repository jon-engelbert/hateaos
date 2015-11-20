package com.example.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;

import com.example.Customer;
import com.example.Exceptions.CustomerNotFoundException;

public interface ICustomerService {
    public Page<Customer> findAllCustomers(Integer pageNumber);
    public Collection<Customer> findAll();
	public void delete(Long arg0) ;
	public Collection<Customer> findByName(String name) ;
	public Customer findOne(Long arg0);
	public <S extends Customer> S save(S arg0) ;
	public Customer deleteById(Long id) throws CustomerNotFoundException;
	public Customer findById(Long id) throws CustomerNotFoundException;
}
