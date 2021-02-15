package org.sid.customerservice;

import org.sid.customerservice.entities.Customer;
import org.sid.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(
			CustomerRepository customerRepository,
			RepositoryRestConfiguration repositoryRestConfiguration
	) {
		return args -> {
			repositoryRestConfiguration.exposeIdsFor(Customer.class);
			customerRepository.save(new Customer(null, "customer 1", "mail 1"));
			customerRepository.save(new Customer(null, "customer 2", "mail 2"));
			customerRepository.save(new Customer(null, "customer 3", "mail 3"));
			customerRepository.save(new Customer(null, "customer 4", "mail 4"));
			customerRepository.findAll().forEach(customer -> System.out.println(customer.toString()));
		};
	}
}
