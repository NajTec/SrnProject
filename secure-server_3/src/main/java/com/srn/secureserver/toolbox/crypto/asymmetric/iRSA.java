package com.srn.secureserver.toolbox.crypto.asymmetric;

import java.io.File;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public interface iRSA 
{
    public void setLength(int length);
    public String privateKey();
    public String publicKey();
    public KeyPair generatedKeyPair();
    public String privateKey(final KeyPair keyPair);
    public String publicKey(final KeyPair keyPair);
    public void storeKeyInFile(final PrivateKey privateKey, final PublicKey publicKey, File file);
    public void storeKeyInFile(final String hexKey, File file);
    public String readKeyFromFile(final String keyPrivatePublic, final File file);
    public String encrypt(final String keyPrivatePublic, final String hexKey, final String plaintext);
    public String decrypt(final String keyPrivatePublic, final String hexKey, final String ciphertext);    
}
