package com.srn.secureserver.components.signature;

import com.srn.secureserver.servercontroller.registration.RegistrationRequest;

public class Signature 
{
    private String hashMail;
    private String publicKey;

    public Signature(){}

    public Signature(final String hashMail, final RegistrationRequest request)
    {
        this.hashMail = hashMail;
        this.publicKey = request.getPublickey();
    }

    public String getHashmail()
    {
        final String hashMail = this.hashMail;

        return hashMail;
    }

    public boolean setHashmail(final String hashMail)
    {
        boolean flag = true;

        if(hashMail == null || hashMail.equals(""))
            flag = false;
        if(flag)
            this.hashMail = hashMail;
            
        return flag;
    }

    public String getPublicKey()
    {
        String publicKey = this.publicKey;

        return publicKey;
    }

    public boolean setPublicKey(final String publicKey)
    {
        boolean flag = true;

        if(publicKey == null || publicKey.equals(""))
            flag = false;
        if(flag)
            this.publicKey = publicKey;

        return flag;
    }
}
