package com.srn.tools.cipher;

import com.srn.tools.crypto.CryptoException;
import com.srn.tools.crypto.Rijndael;
import com.srn.tools.crypto.SHA;
 
public class SSED_v_0_0_1 implements iSSED
{
    @Override
    public final String getCiphertext(final String key, final String plaintext) 
    {
        String ciphertext = null;

        try 
        {
            final SHA hash = new SHA();
            final String messageKey = hash.HMAC("HmacSHA256", "%#PLAINTEXT_Message_KEY_v.0.0.1#", key);
            final String IVKey = hash.HMAC("HmacSHA256", "%#PLAINTEXT_Iv_KEY_v.0.0.1#", key);

            final Rijndael aes = new Rijndael();
            final String IV = aes.initialVector();
            final String cipherMessage = aes.encrypt("AES/CBC/PKCS5Padding", messageKey, IV, plaintext);
            final String cipherIV = aes.encrypt("AES/ECB/PKCS5Padding", IVKey, null, IV);

            ciphertext = "Ciphertext:\n" + cipherMessage + "\n\nCipherIV:\n" + cipherIV;
        } 
        catch (CryptoException e) 
        {
            e.printStackTrace();
        }


        return ciphertext;
    }

    @Override
    public final String getPlaintext(final String key, final String ciphertext) 
    {      
        final String[] vector = ciphertext.split("Ciphertext:\n")[1].split("\n\nCipherIV:\n");
        final String cipherMessage = vector[0];
        final String cipherIV = vector[1];

        String plaintext = null;

        try 
        {
            final SHA hash = new SHA();
            final String messageKey = hash.HMAC("HmacSHA256", "%#PLAINTEXT_Message_KEY_v.0.0.1#", key);
            final String IVKey = hash.HMAC("HmacSHA256", "%#PLAINTEXT_Iv_KEY_v.0.0.1#", key);

            final Rijndael aes = new Rijndael();  
            final String IV = aes.decrypt("AES/ECB/PKCS5Padding", IVKey, null, cipherIV);
            
            plaintext = aes.decrypt("AES/CBC/PKCS5Padding", messageKey, IV, cipherMessage);
        } 
        catch (CryptoException e) 
        {
            e.printStackTrace();
        }
        
        return plaintext;
    }    
}
