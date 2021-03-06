package com.example;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
//import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.dto.CustomerDto;
//import com.example.repository.CustomerRepo;
import com.example.repository.FlightRepo;

class TestUtil {
	 
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
                                                                        MediaType.APPLICATION_JSON.getSubtype(),                        
                                                                        Charset.forName("utf8")                     
                                                                        );
}

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HateaosDemoApplication.class)
public class HateaosDemoApplicationTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
//	@Mock FlightRepo flightRepo;
//	@Mock CustomerRepo customerRepo;

	@Before
//	public void setUp() throws Exception {
//		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
////		Authentication authentication = Mockito.mock(Authentication.class);
////		// Mockito.whens() for your authorization object
////		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
////		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
////		Mockito.when(authentication.getName()).thenReturn("admin@admin.com");
////		SecurityContextHolder.setContext(securityContext);		
//	}
	
	@Test
	public void contextLoads() {
	}
	@Test
	public void findAll_customersFound_ShouldReturnCustomers() throws Exception {
		Customer cust1 = new Customer();
		cust1.setName("bob");
		Customer cust2 = new Customer();
		cust2.setName("boo");
	
//	    when(customerRepo.findAll()).thenReturn(Arrays.asList(cust1, cust2));
	
//	    System.out.println("about to create requestBuilder");
		HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
		CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());
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
