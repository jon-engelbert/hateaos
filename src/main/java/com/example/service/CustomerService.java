package com.example.service;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Customer;
import com.example.Exceptions.CustomerNotFoundException;
import com.example.repository.CustomerRepo;

@Service
@Transactional
public class CustomerService implements ICustomerService{

	private static final int PAGE_SIZE = 5;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	private CustomerRepo customerRepo;
	@Override
    public Page<Customer> findAllCustomers(Integer pageNumber) {
        PageRequest request =
            new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "name");
        return customerRepo.findAll(request);
    }
	public void delete(Long arg0) {
		customerRepo.delete(arg0);
	}
	public Collection<Customer> findByName(String name) {
		return customerRepo.findByName(name);
	}
	public List<Customer> findAll() {
		return customerRepo.findAll();
	}
	public Customer findOne(Long arg0) {
		return customerRepo.findOne(arg0);
	}
	public <S extends Customer> S save(S arg0) {
		return customerRepo.save(arg0);
	}
//    @Transactional(rollbackFor = {TodoNotFoundException.class})
//    @Override
    public Customer deleteById(Long id) throws CustomerNotFoundException { 
        LOGGER.debug("Deleting a Customer with id: {}", id);

        Customer deleted = findById(id);
        LOGGER.debug("Deleting Customer: {}", deleted);

        customerRepo.delete(deleted);
        return deleted;
    }
    
    public Customer findById(Long id) throws CustomerNotFoundException {
    	LOGGER.debug("Finding a customer with id: {}", id);
        Customer found = findOne(id);
        LOGGER.debug("Found Customer: {}", found);
        if (found == null) 
        	throw new CustomerNotFoundException("in findById");
        return found;
    }
}
