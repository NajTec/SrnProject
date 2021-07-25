package com.srn.secureserver.components.storage;

import com.fasterxml.jackson.databind.JsonNode;

public class Save 
{
    private String cipherData;
    private String cipherKey;
    
    public Save()
    {
    }

    public Save(final JsonNode json, final String cipherData)
    {
        this.cipherKey = json.path("cipherkey").textValue(); 
        this.cipherData = cipherData;
    }

    public Save(final String cipherData, final String cipherKey)
    {
        this.cipherData = cipherData;
        this.cipherKey = cipherKey;
    }

    public String getCipherData() 
    {
        return cipherData;
    }

    public void setCipherData(String cipherData) 
    {
        this.cipherData = cipherData;
    }

    public String getCipherKey() 
    {
        return cipherKey;
    }

    public void setCipherKey(String cipherKey) 
    {
        this.cipherKey = cipherKey;
    }
}
