package com.example.controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.example.Customer;
import com.example.dto.CustomerDto;
import com.example.repository.CustomerRepo;
import com.example.service.ICustomerService;


@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
    private ICustomerService customerService;
//	@Autowired 
//	private CustomerRepo customerRepo;
	@RequestMapping(method=RequestMethod.GET)
	Collection<CustomerDto> getAll() {
		Collection<CustomerDto> customerDtos = new ArrayList<CustomerDto>();
		Collection<Customer> customers = this.customerService.findAll();
		for (Customer customer: customers) {
			customerDtos.add(CustomerDto.CreateFromEntity(customer));
		}
		System.out.println("customers size: " + customerDtos.size());
		return customerDtos;
	}

	
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CustomerDto get(@PathVariable("id") Long id) {
    	Customer customer = customerService.findOne(id);
		CustomerDto customerDto = CustomerDto.CreateFromEntity(customer);
        return customerDto;
    }
	
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long  id) {
    	System.out.println("deleting customer:" + id);
//    	Long id = Long.parseLong(idStr);
    	Customer customer = customerService.findOne(id);
		CustomerDto customerDto = CustomerDto.CreateFromEntity(customer);
		customerService.delete(id);
        //return customerDto;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public CustomerDto update(@PathVariable("id") Long id, @RequestBody CustomerDto customerDto) {
    	Customer customer = customerService.findOne(id);
		customerDto.CopyToEntity(customer);
		customerService.save(customer);
		return customerDto;
    }

    @RequestMapping(method=RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto create(@RequestBody CustomerDto customerDto) {
//		System.out.println("creating customer, custDto: " + customerDto.toString());
		Customer customer = new Customer();
		customerDto.CopyToEntity(customer);
		customerService.save(customer);
		return customerDto;
	}
}