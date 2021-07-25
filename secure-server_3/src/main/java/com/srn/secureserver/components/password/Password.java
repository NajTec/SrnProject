package com.srn.secureserver.components.password;

import com.srn.secureserver.servercontroller.registration.RegistrationRequest;

public class Password 
{
    private String hashMail;
    private String password; 

    public Password(){}

    public Password(final String hashMail, final RegistrationRequest request)
    {
        this.hashMail = hashMail;
        this.password = request.getPassword();
    }

    public String getHashmail()
    {
        String email = this.hashMail;

        return email;
    }

    public boolean setHashmail(final String email)
    {
        boolean flag = true;

        if(email == null || email.equals(""))
            flag = false;
        if(flag)
            this.hashMail = email;
            
        return flag;
    }
    
    public String getPassword()
    {
        String password = this.password;

        return password;
    }

    public boolean setPassword(final String password)
    {
        boolean flag = true;

        if(password == null || password.equals(""))
            flag = false;
        if(flag)
            this.password = password;
            
        return flag;
    }
}
