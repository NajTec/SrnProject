package com.srn.secureserver.components.customer;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig 
{
    @Bean
    CommandLineRunner commandLineRunner(final CustomerRepository customerRepository)
    {
        return args -> 
        {
            CustomerERM customer = new CustomerERM(
                "hash", 
                "matthiasutikalat1@gmail.com", 
                "male", "Matthias", "Utikal", "22-11-1995", "Hessen", "Deutschland", "null", 
                "null");

                CustomerERM mcustomer = new CustomerERM(
                "Bhash", 
                "Amatthiasutikalat1@gmail.com", 
                "male", "Matthias", "Utikal", "22-11-1995", "Hessen", "Deutschland", "null", 
                "null");

                customerRepository.saveAll(List.of(customer,mcustomer));
        };
    }
}
