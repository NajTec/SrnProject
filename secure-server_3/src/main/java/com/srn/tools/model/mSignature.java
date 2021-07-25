package com.srn.tools.model;

public class mSignature 
{
    private  String hashMail;
    private String signature;

    public mSignature(){}

    public mSignature(final String hashMail, final String signature)
    {
        this.hashMail = hashMail;
        this.signature = signature;
    }

    public final String getHashMail()
    {
        final String hMail = this.hashMail;

        return hMail;
    }

    public boolean setHashMail(final String hashMail)
    {
        boolean flag = true;

        if(hashMail == null || hashMail.equals(""))
            flag = false;
        if(flag)
            this.hashMail = hashMail;
            
        return flag;
    }

    public final String getSignature()
    {
        final String signature = this.signature;

        return signature;
    }

    public boolean setSignature(final String signature)
    {
        boolean flag = true;

        if(signature == null || signature.equals(""))
            flag = false;
        if(flag)
            this.signature = signature;

        return flag;
    }
}
