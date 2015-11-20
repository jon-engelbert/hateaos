package com.example;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.example.Exceptions.CustomerNotFoundException;
import com.example.controllers.CustomerController;
import com.example.repository.CustomerRepo;
import com.example.repository.FlightRepo;
import com.example.service.CustomerService;
import com.example.service.ICustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HateaosDemoApplication.class)
@WebAppConfiguration
@DirtiesContext(classMode=ClassMode.AFTER_CLASS)	
public class CustomerControllerTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	@Autowired 
	private FlightRepo flightRepo;
	@Autowired
	private CustomerRepo customerRepo;
	@Mock 
	private ICustomerService customerService;

	@InjectMocks
	CustomerController controller;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	    MockitoAnnotations.initMocks(this);
		mockMvc = standaloneSetup(controller).build();
	}
	
	@Test
	public void findAll_customers_ShouldReturnCustomers() throws Exception {
		Customer cust1 = new Customer();
		cust1.setName("bob");
		Customer cust2 = new Customer();
		cust2.setName("boo");
		List<Customer> custList = new ArrayList<Customer>();
		custList.add(cust1);
		custList.add(cust2);
		System.out.println("about to mock customerService");
	    when(customerService.findAll()).thenReturn(custList);
//	    when(customerRepo.findAll()).thenReturn(custList);	// doesn't work, perhaps because findAll is private as member of interface, or perhaps can't mock interface method defn
	
	    System.out.println("about to create requestBuilder");
//		HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
//		CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());
		mockMvc
		.perform(get("/customers"))	//.with(csrf().asHeader()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].name", is("bob")))
        .andExpect(jsonPath("$[1].name", is("boo")));

//verify(todoServiceMock, times(1)).findAll();
//verifyNoMoreInteractions(todoServiceMock);
	}

	@Test
	public void findOne_customerFound_ShouldReturnCustomer() throws Exception {
		Customer cust1 = new Customer();
		cust1.setName("bob");
		Customer cust2 = new Customer();
		cust2.setName("boo");
		List<Customer> custList = new ArrayList<Customer>();
		custList.add(cust1);
		custList.add(cust2);
		System.out.println("about to mock customerService");
	    when(customerService.findById(1L)).thenReturn(cust1);
	    when(customerService.findById(2L)).thenReturn(cust2);
//	    when(customerRepo.findAll()).thenReturn(custList);	// doesn't work, perhaps because findAll is private as member of interface, or perhaps can't mock interface method defn
	
	    System.out.println("about to create requestBuilder");
//		HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
//		CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());
		mockMvc
		.perform(get("/customers/1"))	//.with(csrf().asHeader()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.name", is("bob")));
		mockMvc
		.perform(get("/customers/2"))	//.with(csrf().asHeader()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.name", is("boo")));

//verify(todoServiceMock, times(1)).findAll();
//verifyNoMoreInteractions(todoServiceMock);
	}
	
	@Test
	public void findOne_customerNotFound_ShouldNotReturnCustomer() throws Exception {
		Customer cust1 = new Customer();
		cust1.setName("bob");
		Customer cust2 = new Customer();
		cust2.setName("boo");
		List<Customer> custList = new ArrayList<Customer>();
		custList.add(cust1);
		custList.add(cust2);
		System.out.println("about to mock customerService");
	    when(customerService.findById(1L)).thenReturn(cust1);
	    when(customerService.findById(2L)).thenReturn(cust2);
	    doThrow(new CustomerNotFoundException("Customer 3 not found")).when(customerService).findById(3L);
	    //	    when(customerRepo.findAll()).thenReturn(custList);	// doesn't work, perhaps because findAll is private as member of interface, or perhaps can't mock interface method defn
	
	    System.out.println("about to create requestBuilder");
//		HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
//		CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());
		mockMvc
		.perform(get("/customers/3"))	//.with(csrf().asHeader()))
        .andExpect(status().isNotFound());

//verify(todoServiceMock, times(1)).findAll();
//verifyNoMoreInteractions(todoServiceMock);
	}
	
	@Test
	public void deleteOne_customerFound_ShouldDeleteCustomer() throws Exception {
		Customer cust1 = new Customer();
		cust1.setName("Bob");
		Customer cust2 = new Customer();
		cust2.setName("boo");
		List<Customer> custList = new ArrayList<Customer>();
		custList.add(cust1);
		custList.add(cust2);
	    when(customerService.deleteById(1L)).thenReturn(cust1);
	    when(customerService.findOne(1L)).thenReturn(cust1);
	    when(customerService.findOne(2L)).thenReturn(cust2);
	    mockMvc.perform(delete("/customers/1"))
	    .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is("bob")));
//	    assertEquals(1, custList.size());
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}  
	@Test
	public void updateOne_customerFound_ShouldUpdateCustomer() throws Exception {
		Customer cust1 = new Customer();
		cust1.setName("Bob");
		Customer cust2 = new Customer();
		cust2.setName("boo");
		Customer custNew = new Customer();
		custNew.setName("oTTo");
		List<Customer> custList = new ArrayList<Customer>();
		custList.add(cust1);
		custList.add(cust2);
	    when(customerService.findOne(1L)).thenReturn(cust1);
	    when(customerService.findOne(2L)).thenReturn(cust2);
//	    RequestBody body = RequestBody.create(mediaType, "{\"name\": \"zzz\"}");
	    String requestBodyStr = "{\"name\": \"zzz\"}";
	    mockMvc.perform(put("/customers/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(custNew)))
	    .andExpect(status().isOk());
	    assertEquals(2, custList.size());
	    assertEquals(custNew, custList.get(0));
	}
}
