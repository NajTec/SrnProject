package com.srn.secureserver.components.password;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PasswordService 
{
    private final PasswordRepository passwordRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public PasswordService(final PasswordRepository passwordRepository, 
                            final BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.passwordRepository = passwordRepository;

        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public String signUp(final Password password)
    {
        final String encodedPassword = bCryptPasswordEncoder.encode(password.getPassword());
        final String hashMail = password.getHashmail();
        final PasswordERM passwordERM = new PasswordERM(hashMail, encodedPassword);   

        this.passwordRepository.save(passwordERM);

        return hashMail;
    } 

    public String cleanUp(final String hashMail)
    {
        this.passwordRepository.deleteByHashMail(hashMail);

        final String flag = "Password-Table with hash-email: " + hashMail + " deleted!";

        return flag;
    }

    @Transactional
    public String update(final Password password)
    {
        final String encodedPassword = bCryptPasswordEncoder.encode(password.getPassword());
        final String hashMail = password.getHashmail();

        if(!this.passwordRepository.findByHashMail(hashMail).isPresent())
            throw new IllegalStateException("Email : " + hashMail + " is already taken!");
        

        PasswordERM passwordERM = this.passwordRepository.findByHashMail(hashMail)
                                                            .orElseThrow(() -> 
                                                                new IllegalStateException("customer with hash-mail : " + 
                                                                    hashMail + " does not exist!"));
        
        if(password.getPassword() != null && password.getPassword().length() > 0 && !Objects.equals(passwordERM.getHashPassword(), password.getPassword()))
            passwordERM.setHashPassword(encodedPassword); 

        final String flag = "Password with hash-email: " + hashMail + " updated!";

        return flag;
    }
    
    public boolean loadPasswordByHashMmail(final String hashMail)
    {
        boolean flag = this.passwordRepository.findByHashMail(hashMail).isPresent();

        return flag;
    }
}