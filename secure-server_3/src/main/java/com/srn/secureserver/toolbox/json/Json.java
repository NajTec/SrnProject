package com.srn.secureserver.toolbox.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

@Service
public class Json implements iJson
{

    @Override
    public JsonNode getJson(String payload) 
    {
        JsonNode json = null;

		try 
        {
            json = new ObjectMapper().readTree(payload);
        } 
        catch (JsonProcessingException e) 
        {
            throw new IllegalStateException("Bad JSON format!");
        }

        return json;
    }
    
}