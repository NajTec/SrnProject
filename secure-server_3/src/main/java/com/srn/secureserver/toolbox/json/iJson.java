package com.srn.secureserver.toolbox.json;

import com.fasterxml.jackson.databind.JsonNode;

public interface iJson 
{
    public JsonNode getJson(final String payload);
}
