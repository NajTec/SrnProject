package com.srn.secureserver.toolbox.crypto.symmetric;

import java.io.File;
import java.io.Serializable;

import javax.crypto.SealedObject;

public interface iRijndael 
{
    public String keyGenerator(final int length);
    public String initialVector();
    public String encrypt(final String algorithm, final String hexKey, 
                            final String hexIV, final String plaintext);
    public String decrypt(final String algorithm, final String hexKey, 
                            final String hexIV, final String hexCiphertext);
    public void encryptFile(final String algorithm, final String hexKey, final String hexIV, 
                            final File plaintext, final File hexCiphertext);
    public void decryptFile(final String algorithm, final String hexKey, final String hexIV, 
                            final File hexCiphertext, final File plaintext);
    public SealedObject encryptObject(final String algorithm, final String hexKey, final String hexIV, 
                                        final Serializable object);
    public Serializable decryptObject(final String algorithm, final String hexKey, final String hexIV, 
                                        final SealedObject hexSealedObject);
}
