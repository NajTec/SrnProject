package com.srn.secureserver.components.signature;

import java.util.List;
import java.util.Objects;

import com.srn.secureserver.servercontroller.global.Global;
import com.srn.secureserver.toolbox.crypto.asymmetric.iRSA;
import com.srn.secureserver.toolbox.crypto.hash.iHash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignatureService 
{
    private final SignatureRepository signatureRepository;

    private final iHash hash;
    private final iRSA rsa;

    @Autowired
    public SignatureService(final SignatureRepository signatureRepository, final iRSA rsa,
                                final iHash hash)
    {
        this.signatureRepository = signatureRepository;

        this.hash = hash;
        this.rsa = rsa;
    }

    public List<SignatureERM> signatures()
    {
        return this.signatureRepository.findAll();
    }

    public boolean loadSignatureByHashMmail(final String hashMail)
    {
        boolean flag = this.signatureRepository.findByHashMail(hashMail).isPresent();

        return flag;
    }

    public String signUp(final Signature signature)
    {
        final String hash = this.hash.hash("SHA3-512", signature.getPublicKey());
        final String encryptedPK = this.rsa.encrypt("private", Global.rsa.privateKey(), signature.getPublicKey());
        final String signaturePK = this.rsa.encrypt("private", Global.rsa.privateKey(), hash);
        final String hashMail = signature.getHashmail();
        final SignatureERM passwordERM = new SignatureERM(hashMail, encryptedPK, signaturePK);   

        this.signatureRepository.save(passwordERM);

        return hashMail;
    } 

    public String cleanUp(final String hashMail)
    {
        this.signatureRepository.deleteByHashMail(hashMail);

        final String flag = "Signature-Table with hash-email: " + hashMail + " deleted!";

        return flag;        
    }

    @Transactional
    public String update(final Signature signature)
    {
        final String hashMail = signature.getHashmail();

        if(!this.signatureRepository.findByHashMail(hashMail).isPresent())
            throw new IllegalStateException("Hash-email : " + hashMail + " is already taken!");
        
        SignatureERM signatureERM = this.signatureRepository.findByHashMail(hashMail)
                                                                .orElseThrow(() -> 
                                                                    new IllegalStateException("customer with hash-mail : " + 
                                                                        hashMail + " does not exist!"));

        if(signature.getPublicKey() != null && signature.getPublicKey().length() > 0 && !Objects.equals(signatureERM.getSignature(), signature.getPublicKey()))
        {
            final String encryptedPK = this.rsa.encrypt("private", Global.rsa.privateKey(), signature.getPublicKey());
            signatureERM.setEncryptedPK(encryptedPK);

            final String hash = this.hash.hash("SHA3-512", signature.getPublicKey());
            final String signaturePK = this.rsa.encrypt("private", Global.rsa.privateKey(), hash);
            signatureERM.setSignature(signaturePK);
        }

        final String flag = "Signature with hash-email: " + hashMail + " updated!";

        return flag;
    }
}
