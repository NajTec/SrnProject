package com.srn.secureserver.toolbox.payload;

public class Payload 
{
    private String payload;
    
    public Payload(){}

    public Payload(final String payload)
    {
        this.payload = payload;
    }

    public String getPayload()
    {
        return this.payload;
    }

    public void setPayload(final String payload)
    {
        this.payload = payload;
    }
}
