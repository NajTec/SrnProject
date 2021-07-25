package com.srn.secureserver.components.customer;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import com.srn.secureserver.toolbox.crypto.hash.iHash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService
{
    private final CustomerRepository customerRepository;

    private final iHash hash;
    

    @Autowired
    public CustomerService(final CustomerRepository customerRepository, final iHash hash)
    {
        this.customerRepository = customerRepository;

        this.hash = hash;
    }

    public List<CustomerERM> customers()
    {
        return this.customerRepository.findAll();
    }
    
    public boolean loadCustomerByEmail(final String email) throws UsernameNotFoundException 
    {
        boolean flag = this.customerRepository.findByEmail(email).isPresent();

        return flag;
    }

    public String hashMail(final String email)
    {
        CustomerERM customerERM = this.customerRepository.findByEmail(email)
                                                            .orElseThrow(() ->
                                                                new IllegalStateException("Customer with email: " + email + " does not exist!"));

        final String hashMail = customerERM.getHashmail();

        return hashMail;
    }

    public String signUp(final Customer customer)
    {
        final String hashMail = this.hash.hash("SHA3-512", customer.getEmail());
        final CustomerERM customerERM = new CustomerERM(hashMail, customer.getEmail(), customer.getGender(), customer.getFirstname(),
                                                    customer.getLastname(), customer.getBirthday(), customer.getBirthplace(),
                                                    customer.getCountry(), customer.getState(), customer.getStreet());

        this.customerRepository.save(customerERM);

        return hashMail;
    }

    public String cleanUp(final String hashMail)
    {
        this.customerRepository.deleteByHashMail(hashMail);

        final String flag = "Customerhash-email: " + hashMail + " deleted!";

        return flag;
    }

    @Transactional
    public String update(Customer customer)
    {
        final String hashMail = customer.getHashmmail();

        if(!this.customerRepository.findByHashMail(hashMail).isPresent())
            throw new IllegalStateException("Email : " + customer.getEmail() + " is already taken!");

        CustomerERM customerERM = this.customerRepository.findByHashMail(hashMail)
                                                            .orElseThrow(() -> 
                                                                new IllegalStateException("customer with hash-mail : " + 
                                                                    hashMail + " does not exist!"));

        if(customer.getEmail() != null && customer.getEmail().length() > 0 && !Objects.equals(customerERM.getEmail(), customer.getEmail()))
        {
            customerERM.setEmail(customer.getEmail());
            customerERM.setHashmail(this.hash.hash("SHA3-512", customer.getEmail())); //TODO check if it works!
        }
                                                            
        if(customer.getGender() != null && customer.getGender().length() > 0 && !Objects.equals(customerERM.getGender(), customer.getGender()))
            customerERM.setGender(customer.getGender());
                                                            
        if(customer.getFirstname() != null && customer.getFirstname().length() > 0 && !Objects.equals(customerERM.getFirstname(), customer.getFirstname()))
            customerERM.setFirstname(customer.getFirstname());
                                                            
        if(customer.getLastname() != null && customer.getLastname().length() > 0 && !Objects.equals(customerERM.getLastname(), customer.getLastname()))
            customerERM.setLastname(customer.getLastname());
                                                            
        if(customer.getBirthday() != null && customer.getBirthday() .length() > 0 && !Objects.equals(customerERM.getBirthday(), customer.getBirthday()))
            customerERM.setBirthday(customer.getBirthday());
                                                            
        if(customer.getCountry() != null && customer.getCountry().length() > 0 && !Objects.equals(customerERM.getCountry(), customer.getCountry()))
            customerERM.setCountry(customer.getCountry());
                                                            
        if(customer.getState() != null && customer.getState().length() > 0 && !Objects.equals(customerERM.getState(), customer.getState()))
            customerERM.setState(customer.getState());
                                                            
        if(customer.getStreet() != null && customer.getStreet().length() > 0 && !Objects.equals(customerERM.getStreet(), customer.getStreet()))
            customerERM.setStreet(customer.getStreet());    

        final String flag = "Customer with hash-email: " + hashMail + " updated!";

        return flag;
    }
}
