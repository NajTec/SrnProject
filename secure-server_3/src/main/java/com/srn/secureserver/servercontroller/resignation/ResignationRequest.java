package com.srn.secureserver.servercontroller.resignation;

public class ResignationRequest 
{
    private String hashMail;

    public ResignationRequest(final String hashMail)
    {
        this.hashMail = hashMail;
    }

    public String getHashmail() 
    {
        return hashMail;
    }

    public void setHashmail(String hashMail) 
    {
        this.hashMail = hashMail;
    }    
}
