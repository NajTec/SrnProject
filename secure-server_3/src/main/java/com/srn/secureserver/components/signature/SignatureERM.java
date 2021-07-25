package com.srn.secureserver.components.signature;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.ToString;

@Entity(name = "Signature")
@Table(name = "signature")
@ToString
public class SignatureERM 
{
    @Id
    @SequenceGenerator(name = "signature_sequence", sequenceName = "signature_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "signature_sequence")
    @Column(name = "id", nullable = false, columnDefinition = "TEXT", unique = true)
    private Long id;
    @Column(name = "hashMail", nullable = false, columnDefinition = "TEXT")
    private String hashMail;
    @Column(name = "encryptedPK", nullable = false, columnDefinition = "TEXT")
    private String encryptedPK;
    @Column(name = "signature", nullable = false, columnDefinition = "TEXT")
    private String signature;

    public SignatureERM(){}

    public SignatureERM(final String hashMail, final String encryptedPK, final String signature)
    {
        this.hashMail = hashMail;
        this.encryptedPK = encryptedPK;
        this.signature = signature;
    }

    public Long getId()
    {
        Long id = this.id;

        return id;
    }

    public boolean setId(final Long id)
    {
        boolean flag = true;

        if(id == null)
            flag = false;
        if(flag)
            this.id = id;
            
        return flag;
    }

    public String getHashmail()
    {
        final String hashMail = this.hashMail;

        return hashMail;
    }

    public boolean setHashmail(final String hashMail)
    {
        boolean flag = true;

        if(hashMail == null || hashMail.equals(""))
            flag = false;
        if(flag)
            this.hashMail = hashMail;
            
        return flag;
    }

    public String getEncryptedPK()
    {
        String encryptedPK = this.encryptedPK;

        return encryptedPK;
    }

    public boolean setEncryptedPK(final String encryptedPK)
    {
        boolean flag = true;

        if(encryptedPK == null || encryptedPK.equals(""))
            flag = false;
        if(flag)
            this.encryptedPK = encryptedPK;

        return flag;
    }

    public String getSignature()
    {
        String signature = this.signature;

        return signature;
    }

    public boolean setSignature(final String signature)
    {
        boolean flag = true;

        if(signature == null || signature.equals(""))
            flag = false;
        if(flag)
            this.signature = signature;

        return flag;
    }
}
