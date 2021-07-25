package com.srn.secureserver.toolbox.validator.email;

import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator implements Predicate<String>, iEmailValidator
{
    @Override
    public boolean test(String t) 
    {
        return false;
    }    
}
