package com.srn.tools.crypto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.security.crypto.codec.Hex;

public class RSA 
{
    private KeyPairGenerator generator;
    private KeyPair keyPair;

    public RSA(int length)
    {
        try 
        {
            this.generator = KeyPairGenerator.getInstance("RSA");
            this.generator.initialize(length);
            this.keyPair = generator.generateKeyPair();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
    }    

    public final String privateKey() throws CryptoException
    {
        PrivateKey key = null;
        key = this.keyPair.getPrivate();

        byte[] bytes = key.getEncoded();

        String stringKey = new String(Hex.encode(bytes));
        
        return stringKey;
    }

    public final String publicKey() throws CryptoException
    {
        PublicKey key = null;
        key = this.keyPair.getPublic();
        
        byte[] bytes = key.getEncoded();

        String stringKey = new String(Hex.encode(bytes));
        
        return stringKey;
    }

    public final KeyPair generatedKeyPair() throws CryptoException
    {
        KeyPair keyPair = null; 
        keyPair = this.generator.generateKeyPair();

        if(keyPair == null)
            throw new CryptoException("RSA.java: generatedKeyPair - result is null!");

        return keyPair;
    }

    public final String privateKey(final KeyPair keyPair) throws CryptoException
    {
        PrivateKey key = null;
        key = keyPair.getPrivate();

        byte[] bytes = key.getEncoded();

        String stringKey = new String(Hex.encode(bytes));
        
        return stringKey;
    }

    public final String publicKey(final KeyPair keyPair) throws CryptoException
    {
        PublicKey key = null;
        key = keyPair.getPublic();
        
        byte[] bytes = key.getEncoded();

        String stringKey = new String(Hex.encode(bytes));
        
        return stringKey;
    }

    public final void storeKeyInFile(final PrivateKey privateKey, 
                                                final PublicKey publicKey, File file) throws CryptoException
    {
        if((privateKey != null && publicKey == null) || (privateKey == null && publicKey != null))
        {
            try
            {
                FileOutputStream fos = new FileOutputStream(file);

                if(privateKey != null)
                    fos.write(new String(Hex.encode(privateKey.getEncoded())).getBytes(StandardCharsets.UTF_8));
                if(publicKey != null)
                    fos.write(new String(Hex.encode(publicKey.getEncoded())).getBytes(StandardCharsets.UTF_8));

                fos.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
        else
        {
            throw new CryptoException("RSA.java: storeKeyInFile - result is null!");
        }
    }

    public final void storeKeyInFile(final String hexKey, File file) throws CryptoException
    {
        if(hexKey != null)
        {
            try
            {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(hexKey.getBytes(StandardCharsets.UTF_8));
                fos.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
        else
        {
            throw new CryptoException("RSA.java: storeKeyInFile - result is null!");
        }
    }

    public final String readKeyFromFile(final String keyPrivatePublic, final File file) throws CryptoException
    {
        String stringKey = null;

        if(keyPrivatePublic.equals("public") || keyPrivatePublic.equals("private"))
        {
            try 
            {
                byte[] keyBytes = Hex.decode(new String(Files.readAllBytes(file.toPath())));
                //EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");

                if(keyPrivatePublic.equals("public"))
                    stringKey = new String(Hex.encode(keyFactory.generatePublic(new X509EncodedKeySpec(keyBytes)).getEncoded()));
                if(keyPrivatePublic.equals("private"))
                    stringKey = new String(Hex.encode(keyFactory.generatePrivate(new PKCS8EncodedKeySpec(keyBytes)).getEncoded()));
            } 
            catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) 
            {
                e.printStackTrace();
            }
        }
        else
        {
            throw new CryptoException("RSA.java: readKeyFromFile - key is not public or private!");
        }

        if(stringKey == null)
            throw new CryptoException("RSA.java: readKeyFromFile - result is null!");

        return stringKey;
    }

    public final String encrypt(final String keyPrivatePublic, final String hexKey, final String plaintext) throws CryptoException
    {
        String encryption = null;

        if(keyPrivatePublic.equals("public") || keyPrivatePublic.equals("private"))
        {
            try
            {
                byte[] keyBytes = Hex.decode(hexKey);
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                Cipher cipher = Cipher.getInstance("RSA");

                if(keyPrivatePublic.equals("public"))
                    cipher.init(Cipher.ENCRYPT_MODE, keyFactory.generatePublic(new X509EncodedKeySpec(keyBytes)));
                if(keyPrivatePublic.equals("private"))
                    cipher.init(Cipher.ENCRYPT_MODE, keyFactory.generatePrivate(new PKCS8EncodedKeySpec(keyBytes)));
                
                byte[] bytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
                
                encryption = new String(Hex.encode(bytes));
            }
            catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                    InvalidKeySpecException | IllegalBlockSizeException | BadPaddingException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            throw new CryptoException("RSA.java: encrypt - key is not public or private!");
        }

        if(encryption == null)
            throw new CryptoException("RSA.java: encrypt - result is null!");

        return encryption;
    }

    public final String decrypt(final String keyPrivatePublic, final String hexKey, final String ciphertext) throws CryptoException
    {
        String decryption = null;

        if(keyPrivatePublic.equals("public") || keyPrivatePublic.equals("private"))
        {
            try
            {
                byte[] keyBytes = Hex.decode(hexKey);
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                Cipher cipher = Cipher.getInstance("RSA");

                if(keyPrivatePublic.equals("public"))
                    cipher.init(Cipher.DECRYPT_MODE, keyFactory.generatePublic(new X509EncodedKeySpec(keyBytes)));
                if(keyPrivatePublic.equals("private"))
                    cipher.init(Cipher.DECRYPT_MODE, keyFactory.generatePrivate(new PKCS8EncodedKeySpec(keyBytes)));
                
                byte[] bytes = cipher.doFinal(Hex.decode(ciphertext));

                decryption = new String(bytes, StandardCharsets.UTF_8);
            }
            catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                    InvalidKeySpecException | IllegalBlockSizeException | BadPaddingException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            throw new CryptoException("RSA.java: decrypt - key is not public or private!");
        }

        if(decryption == null)
            throw new CryptoException("RSA.java: decrypt - result is null!");

        return decryption;
    }
}
