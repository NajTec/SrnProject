package com.srn.tools.cipher;

public interface iSSED 
{
    public String getCiphertext(final String key, final String plaintext);
    public String getPlaintext(final String key, final String ciphertext);
}
