package com.srn.secureserver.components.storage;

import com.srn.secureserver.servercontroller.registration.RegistrationRequest;

public class Storage 
{
    private String hashMail;
    private String email;
    private String masterKey;

    public Storage(final String hashMail, final RegistrationRequest request)
    {
        this.hashMail = hashMail;
        this.email = request.getEmail();
        this.masterKey = request.getMasterkey();
    }

    public String getHashmail() 
    {
        return hashMail;
    }

    public void setHashmail(final String hashMail) 
    {
        this.hashMail = hashMail;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(final String email) 
    {
        this.email = email;
    }

    public String getMasterkey() 
    {
        return masterKey;
    }

    public void setMasterkey(final String masterKey) 
    {
        this.masterKey = masterKey;
    }

    
}
