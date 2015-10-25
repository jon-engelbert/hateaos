package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HateaosDemoApplication.class)
public class HateaosDemoApplicationTests {

	@Mock FlightRepo flightRepo;
	@Mock CustomerRepo customerRepo;
	@Test
	public void contextLoads() {
	}

}
