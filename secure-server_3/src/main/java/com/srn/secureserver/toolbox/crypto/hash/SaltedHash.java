package com.srn.secureserver.toolbox.crypto.hash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Service;

@Service
public class SaltedHash implements iSaltedHash
{
    @Override
    public mHash encrypt(final String level, final String value) 
    {  
        mHash mHashObject = null;

        if(level.equals("weak"))
            mHashObject = encryption_0(value);
        if(level.equals("strong"))
            mHashObject = encryption_1(value);

        return mHashObject;
    }

    private mHash encryption_0(final String value)
    {
        byte[] hashedPassword = null; 
        byte[] bytes = null;

        try 
        {
            bytes = new byte[16];

            SecureRandom random = new SecureRandom();
            random.nextBytes(bytes);

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(bytes);

            hashedPassword = messageDigest.digest(value.getBytes(StandardCharsets.UTF_8));
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }

        final String hash = new String(Hex.encode(hashedPassword));
        final String salt = new String(Hex.encode(bytes));

        final mHash mHashData = new mHash(hash, salt);

        return mHashData;
    }
    
    private mHash encryption_1(final String value)
    {
        byte[] hashedPassword = null; 
        byte[] bytes = null;

        try 
        {
            bytes = new byte[16];

            SecureRandom random = new SecureRandom();
            random.nextBytes(bytes);

            KeySpec spec = new PBEKeySpec(value.toCharArray(), bytes, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            hashedPassword = factory.generateSecret(spec).getEncoded();
        } 
        catch (NoSuchAlgorithmException | InvalidKeySpecException e) 
        {
            e.printStackTrace();
        }

        final String hash = new String(Hex.encode(hashedPassword));
        final String salt = new String(Hex.encode(bytes));

        final mHash mHashData = new mHash(hash, salt);

        return mHashData; 
    }    
}
