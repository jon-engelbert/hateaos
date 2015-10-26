package com.example;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.controllers.CustomerController;
import com.example.repository.CustomerRepo;
import com.example.repository.FlightRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HateaosDemoApplication.class)
@WebAppConfiguration
@DirtiesContext(classMode=ClassMode.AFTER_CLASS)	
public class CustomerControllerTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	@Autowired FlightRepo flightRepo;
	@Autowired
	CustomerRepo customerRepo;

	@InjectMocks
	CustomerController controller;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void findAll_customersFound_ShouldReturnCustomers() throws Exception {
		Customer cust1 = new Customer();
		cust1.setName("bob");
		Customer cust2 = new Customer();
		cust2.setName("boo");
	
//	    when(customerRepo.findAll()).thenReturn(Arrays.asList(cust1, cust2));
	
//	    System.out.println("about to create requestBuilder");
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
}
