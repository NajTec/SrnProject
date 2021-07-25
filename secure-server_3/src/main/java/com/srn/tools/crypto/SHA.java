package com.srn.tools.crypto;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.crypto.codec.Hex;

public class SHA 
{
    public final String HASH(final String algorithm, final String plaintext) throws CryptoException
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
        finally
        {}

        if(result == null)
            throw new CryptoException("SHA.java: HASH - result is null!");

        return result;
    }

    public final String HMAC(final String algorithm, final String key, final String plaintext) throws CryptoException
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
        finally 
        {}

        if(result == null)
            throw new CryptoException("SHA.java: HMAC - result is null!");

        return result;
    }
}
