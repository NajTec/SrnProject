package com.srn.secureserver.components.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "User")
public class CustomerController 
{
    private final CustomerService customerService;

    @Autowired
    public CustomerController(final CustomerService customerService)
    {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerERM> customers()
    {
        return this.customerService.customers();
    }

    @PutMapping(path = ":{CustomerHashMail}")
    public void updateCustomer(@PathVariable("CustomerHashMail") final Customer customer)
    {
        this.customerService.update(customer);
    }
}
