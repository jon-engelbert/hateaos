package com.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.naming.NamingException;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class HateaosDemoApplication {

    public HateaosDemoApplication() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
        SpringApplication.run(HateaosDemoApplication.class, args);
    }
}


@Component
class BookingCommandLineRunner implements CommandLineRunner {
	@Autowired CustomerRepo customerRepo;
	
	@Override
	public void run(String... arg0) throws Exception {
		for (Customer c : this.customerRepo.findAll()) {
			System.out.println(c.toString());
			for (Flight f : c.getFlights()) {
				System.out.println(f.toString());
			}
		}
		
	}
}

@RestController
class BookingRestController {
	@Autowired 
	private CustomerRepo CustomerRepo;
	@RequestMapping("/customers")
	Collection<Customer> Bookings() {
		return this.CustomerRepo.findAll();
	}
	
	@Autowired 
	private FlightRepo flightRepo;
	@RequestMapping("/flights")
	Collection<Flight> Flights() {
		return this.flightRepo.findAll();
	}
}

@Entity
class Flight {
	public Flight() {
	}
	public Flight(String flightName) {
		this.name = flightName;
		this.customers = new ArrayList<Customer>();
	}
	@Id @GeneratedValue
	private Long id;
	private String name;
	@ManyToMany
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

@Entity
class Customer {
	public Customer() {
	}
	public Customer(String name) {
		this.name = name;
		this.flights = new ArrayList<Flight>();
	}
	@Id @GeneratedValue
	private Long id;
	private String name;
    @JoinTable(name = "customer_flights", joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "flight_id", referencedColumnName = "id") )
	@ManyToMany	(fetch = FetchType.EAGER)
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
}


@Configuration
@ComponentScan(basePackages = { "com.example" })
//@PropertySource("classpath:email.properties")
class AppConfig {
}

@EnableJpaRepositories(basePackages = { "com.example" })
@Configuration
class DataSourceConfig {
	
    @Value("${spring.datasource.driverClassName}")
    private String databaseDriverClassName;
 
    @Value("${spring.datasource.url}")
    private String datasourceUrl;
 
    @Value("${spring.datasource.username}")
    private String databaseUsername;
 
    @Value("${spring.datasource.password}")
    private String databasePassword;
    
	@Bean
	@Profile("prod")
	public DataSource postgresqlDataSource() throws NamingException {
        org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
        ds.setDriverClassName(databaseDriverClassName);
        ds.setUrl(datasourceUrl);
        ds.setUsername(databaseUsername);
        ds.setPassword(databasePassword);
 
        return ds;
    }

//	@Bean(name="dataSource")
//	@Profile("prod")
//	public DataSource getProdDataSource() throws NamingException {
//        Context ctx = new InitialContext();
//        // TODO: set the path to the production database
//        return (DataSource) ctx.lookup("java:comp/env/jdbc/datasource");
//	  }	
//    @Bean
//	@Profile("dev")
//	public DataSource embeddedDataSource() {
//        return new EmbeddedDatabaseBuilder()
//        .setType(EmbeddedDatabaseType.HSQL)
//        .addScript("src/main/resources/schema.sql")
//        .addScript("src/main/resources/data.sql")
//        .build();
//	  }	
	
}