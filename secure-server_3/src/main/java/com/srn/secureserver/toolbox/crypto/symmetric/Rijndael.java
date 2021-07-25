package com.srn.secureserver.toolbox.crypto.symmetric;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Service;

@Service
public class Rijndael implements iRijndael
{
    public Rijndael(){}

    @Override
    public final String keyGenerator(final int length)
    {
        String stringKey = null;

        if(length == 128 || length == 192 || length == 256)
        {
            try
            {
                KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                keyGenerator.init(length);
    
                SecretKey secretKey = keyGenerator.generateKey();
    
                stringKey = new String(Hex.encode(secretKey.getEncoded()));
            } 
            catch (NoSuchAlgorithmException e)
            {
                e.printStackTrace();
            }
        }  
        else
        {
            throw new IllegalStateException("Rijndael.java: keyGenerator - length has to be one of these values: 128, 192, 256!");
        } 
        
        if(stringKey == null)
            throw new IllegalStateException("Rijndael.java: keyGenerator - result is null!");

        return stringKey;
    }

    @Override
    public final String initialVector()
    {
        String vector = null;
        final int length = 128;

        byte[] bytes = new byte[length / 8];
        new SecureRandom().nextBytes(bytes);

        IvParameterSpec ivParameterSpec = new IvParameterSpec(bytes);

        vector = new String(Hex.encode(ivParameterSpec.getIV()));
        
        return vector;
    }

    @Override
    public final String encrypt(final String algorithm, final String hexKey, final String hexIV, final String plaintext) 
    {
        String encryption = null;

        try 
        {
            if(hexIV != null)
            {
                byte[] bytes = Hex.decode(hexKey);
                SecretKey secretKey = new SecretKeySpec(bytes, 0, bytes.length, "AES");
                IvParameterSpec ivParameterSpec = new IvParameterSpec(Hex.decode(hexIV));
                            
                Cipher cipher = Cipher.getInstance(algorithm);
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

                byte[] cipherText = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

                encryption = new String(Hex.encode(cipherText));
            }
            if(hexIV == null)
            {
                if(!algorithm.contains("ECB"))
                    throw new IllegalStateException("Rijndael.java: encrypt - algorithm does not contain ECB!");

                byte[] bytes = Hex.decode(hexKey);
                SecretKey secretKey = new SecretKeySpec(bytes, 0, bytes.length, "AES");

                Cipher cipher = Cipher.getInstance(algorithm);
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);

                byte[] ciphertext = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

                encryption = new String(Hex.encode(ciphertext));
            }
        } 
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | 
                InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) 
        {
            e.printStackTrace();
        }

        if(encryption == null)
            throw new IllegalStateException("Rijndael.java: encrypt - result is null!");

        return encryption;
    }

    @Override
    public final String decrypt(final String algorithm, final String hexKey, final String hexIV, final String hexCiphertext)
    {
        String decoded = null;

        try 
        {
            byte[] bytes = Hex.decode(hexKey);
            SecretKey secretKey = new SecretKeySpec(bytes, 0, bytes.length, "AES");
            Cipher cipher = Cipher.getInstance(algorithm);

            if(hexIV != null)
            {
                IvParameterSpec ivParameterSpec = new IvParameterSpec(Hex.decode(hexIV));

                cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);

                byte[] plaintext = cipher.doFinal(Hex.decode(hexCiphertext));

                decoded = new String(plaintext, StandardCharsets.UTF_8);
            }
            if(hexIV == null)
            {
                cipher.init(Cipher.DECRYPT_MODE, secretKey);

                byte[] plaintext = cipher.doFinal(Hex.decode(hexCiphertext));

                decoded = new String(plaintext, StandardCharsets.UTF_8);
            }
        } 
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | 
                InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) 
        {
            e.printStackTrace();
        }      
        
        if(decoded == null)
            throw new IllegalStateException("Rijndael.java: decrypt - result is null!");

        return decoded;
    }

    @Override
    public void encryptFile(final String algorithm, final String hexKey, final String hexIV, 
                                            final File plaintext, final File hexCiphertext)
    {
        try 
        {
            byte[] bytes = Hex.decode(hexKey);
            SecretKey secretKey = new SecretKeySpec(bytes, 0, bytes.length, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(Hex.decode(hexIV));
                        
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
            
            FileInputStream inputStream = new FileInputStream(plaintext);
            FileOutputStream outputStream = new FileOutputStream(hexCiphertext);

            byte[] buffer = new byte[64];
            int bytesRead;
            byte[] outputBytes;

            while((bytesRead = inputStream.read(buffer)) != -1)
            {
                outputBytes = cipher.update(buffer, 0, bytesRead); // byte[] outputBytes = cipher.update(buffer, 0, bytesRead); 
                if(outputBytes != null)
                    outputStream.write(new String(Hex.encode(outputBytes)).getBytes(StandardCharsets.UTF_8));
            }
            
            outputBytes = cipher.doFinal(); // byte[] outputBytes = cipher.doFinal(); 
            if(outputBytes != null)
                outputStream.write(new String(Hex.encode(outputBytes)).getBytes(StandardCharsets.UTF_8));

            inputStream.close();
            outputStream.close();
        } 
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | 
                InvalidAlgorithmParameterException | IllegalBlockSizeException | 
                BadPaddingException | IOException e) 
        {
            e.printStackTrace();
        }
    }

    @Override
    public void decryptFile(final String algorithm, final String hexKey, final String hexIV, 
                                            final File hexCiphertext, final File plaintext) 
    {
        try 
        {
            byte[] bytes = Hex.decode(hexKey);
            SecretKey secretKey = new SecretKeySpec(bytes, 0, bytes.length, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(Hex.decode(hexIV));
                        
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            
            FileInputStream inputStream = new FileInputStream(hexCiphertext);
            FileOutputStream outputStream = new FileOutputStream(plaintext);

            byte[] buffer = new byte[64];
            int bytesRead;

            while((bytesRead = inputStream.read(buffer)) != -1)
            {
                byte[] outputBytes = cipher.update(Hex.decode(new String(buffer, StandardCharsets.UTF_8)), 0, bytesRead / 2);
                if(outputBytes != null)
                    outputStream.write(outputBytes);
            }
            
            byte[] outputBytes = cipher.doFinal();
            if(outputBytes != null)
                outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();
        } 
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | 
                InvalidAlgorithmParameterException | IllegalBlockSizeException | 
                BadPaddingException | IOException e) 
        {
            e.printStackTrace();
        }
    }

    @Override
    public final SealedObject encryptObject(final String algorithm, final String hexKey, final String hexIV, 
                                                final Serializable object) 
    {
        SealedObject sealedObject = null;

        try 
        {
            byte[] bytes = Hex.decode(hexKey);
            SecretKey secretKey = new SecretKeySpec(bytes, 0, bytes.length, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(Hex.decode(hexIV));

            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

            sealedObject = new SealedObject(object, cipher);
        } 
        catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException |
                InvalidKeyException | IOException | IllegalBlockSizeException e) 
        {
            e.printStackTrace();
        }

        if(sealedObject == null)
            throw new IllegalStateException("Rijndael.java: encryptObject - result is null!");
            
        return sealedObject;
    }

    @Override
    public final Serializable decryptObject(final String algorithm, final String hexKey, final String hexIV, 
                                                final SealedObject hexSealedObject)
    {
        Serializable unsealObject = null;

        try 
        {
            byte[] bytes = Hex.decode(hexKey);
            SecretKey secretKey = new SecretKeySpec(bytes, 0, bytes.length, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(Hex.decode(hexIV));

            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);

            unsealObject = (Serializable) hexSealedObject.getObject(cipher);
        } 
        catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException |
                InvalidKeyException | IOException | IllegalBlockSizeException | ClassNotFoundException | 
                BadPaddingException e) 
        {
            e.printStackTrace();
        }

        if(unsealObject == null)
            throw new IllegalStateException("Rijndael.java: decryptObject - result is null!");
            
        return unsealObject;
    }
}
