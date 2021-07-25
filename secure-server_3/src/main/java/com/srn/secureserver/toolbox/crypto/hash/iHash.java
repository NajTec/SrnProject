package com.srn.secureserver.toolbox.crypto.hash;

public interface iHash 
{
    public String hash(final String algorithm, final String plaintext);
    public String hmac(final String algorithm, final String key, final String plaintext);
}
