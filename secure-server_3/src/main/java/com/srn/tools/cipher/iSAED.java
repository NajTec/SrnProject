package com.srn.tools.cipher;

public interface iSAED 
{
    public String getCiphertext(final String keyPrivatePublic, final String key, final String plaintext);
    public String getPlaintext(final String keyPrivatePublic, final String key, final String ciphetext);

    public String getSignature(final String keyPrivatePublic, final String key, final String plaintext);

    public String getPublicKey();
    public String getPrivateKey();
}
