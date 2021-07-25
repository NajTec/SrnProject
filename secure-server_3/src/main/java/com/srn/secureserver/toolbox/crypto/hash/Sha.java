package com.srn.secureserver.toolbox.crypto.hash;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Service;

@Service
public class Sha implements iHash
{
    public Sha(){}

    @Override
    public String hash(final String algorithm, final String plaintext) 
    {
        String result = null;

        try 
        {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            final byte[] hashbytes = messageDigest.digest(plaintext.getBytes(StandardCharsets.UTF_8));

            result = new String(Hex.encode(hashbytes));
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }

        if(result == null)
            throw new IllegalStateException("SHA.java: HASH - result is null!");

        return result;
    }

    @Override
    public String hmac(final String algorithm, final String key, final String plaintext) 
    {
        String result = null;

        try
        {
            final byte[] byteKey = key.getBytes(StandardCharsets.UTF_8);
            Mac sha512Hmac = Mac.getInstance(algorithm);

            SecretKeySpec keySpec = new SecretKeySpec(byteKey, algorithm);
            sha512Hmac.init(keySpec);

            final byte[] macData = sha512Hmac.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

            result = new String(Hex.encode(macData));
        }
        catch (InvalidKeyException | NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        if(result == null)
            throw new IllegalStateException("SHA.java: HMAC - result is null!");

        return result;
    }
    
}
