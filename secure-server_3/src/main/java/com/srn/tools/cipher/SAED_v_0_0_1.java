package com.srn.tools.cipher;

import com.srn.tools.crypto.CryptoException;
import com.srn.tools.crypto.RSA;
import com.srn.tools.crypto.SHA;

public class SAED_v_0_0_1 implements iSAED
{
    RSA rsa = null;
    String publicKey;
    String privateKey;

    public SAED_v_0_0_1(final int length)
    {
        this.publicKey = null;
        this.privateKey = null;
        
        try 
        {
            this.rsa = new RSA(length);
            this.publicKey = this.rsa.publicKey();
            this.privateKey = this.rsa.privateKey();
        } 
        catch (CryptoException e) 
        {
            e.printStackTrace();
        }
    }

    @Override
    public final String getPrivateKey()
    {
        final String key = this.privateKey;

        return key;
    }

    @Override
    public final String getPublicKey()
    {
        final String key = this.publicKey;

        return key;
    }

    @Override
    public final String getCiphertext(final String keyPrivatePublic, final String key, final String plaintext) 
    {
        String encryption = null;
        
        try 
        {
            encryption = this.rsa.encrypt(keyPrivatePublic, key, plaintext);
        } 
        catch (CryptoException e) 
        {
            e.printStackTrace();
        }
        
        return encryption;
    }

    @Override
    public final String getPlaintext(final String keyPrivatePublic, final String key, final String ciphertext) 
    {
        String decryption = null;

        try 
        {
            decryption = this.rsa.decrypt(keyPrivatePublic, key, ciphertext);
        } 
        catch (CryptoException e) 
        {
            e.printStackTrace();
        }

        return decryption;
    }

    @Override
    public final String getSignature(final String keyPrivatePublic, final String key, final String plaintext) 
    {
        String signature = null;

        try 
        {
            final SHA sha = new SHA();
            final String hash = sha.HASH("SHA3-512", plaintext);

            signature = this.rsa.encrypt(keyPrivatePublic, key, hash);
        } 
        catch (CryptoException e) 
        {
            e.printStackTrace();
        }

        return signature;
    }    
}
