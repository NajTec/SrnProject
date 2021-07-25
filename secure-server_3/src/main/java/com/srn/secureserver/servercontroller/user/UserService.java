package com.srn.secureserver.servercontroller.user;

import java.time.LocalDateTime;
import java.util.UUID;

import com.srn.secureserver.components.customer.CustomerService;
import com.srn.secureserver.servercontroller.token.ConfirmationToken;
import com.srn.secureserver.servercontroller.token.ConfirmationTokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService // damit werden user gefunden beil login
{
    private final CustomerService customerService;
    private final UserRepository userRepository;
    private final ConfirmationTokenService confirmationTokenService;


    @Autowired
    public UserService(final CustomerService customerService, final UserRepository userRepository, 
                        final ConfirmationTokenService confirmationTokenService)
    {
        this.customerService = customerService;
        this.userRepository = userRepository;
        this.confirmationTokenService = confirmationTokenService;
    }
 
    public String signUp(User user)
    {
        final String hashMail = user.getHashMail();
        final UserERM userERM = new UserERM(hashMail, user.getUserRole(), user.getLocked(), user.getEnabled());   

        this.userRepository.save(userERM);

        final String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), 
                                                LocalDateTime.now().plusMinutes(30), userERM);

        this.confirmationTokenService.save(confirmationToken);

        final String flag = token; 

        return flag;
    }

    public boolean loadUserByHashMail(final String hashMail)
    {
        boolean flag = this.userRepository.findByEmail(hashMail).isPresent();

        return flag;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException 
    {
        final String customerHashMail = this.customerService.hashMail(username);
        final UserERM userERM = this.userRepository.findByEmail(customerHashMail)
                                                    .orElseThrow(() ->
                                                        new IllegalStateException("Customer with hash-email" + 
                                                            customerHashMail + " does not exist!"));


        return userERM;
    }
    
    @Transactional
    public int enable(String email)
    {
        final String hashMail = this.customerService.hashMail(email);
        int flag = this.userRepository.enableUserERM(hashMail);

        return flag;
    }
}
