package com.srn.secureserver.components.storage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.ToString;

@Entity(name = "Storage")
@Table(name = "storage")
@ToString
public class StorageERM 
{
    @Id
    @SequenceGenerator(name = "storage_sequence", sequenceName = "storage_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "storage_sequence")
    @Column(name = "id", nullable = false, columnDefinition = "TEXT", unique = true)
    private Long id;
    @Column(name = "hashMail", nullable = false, columnDefinition = "TEXT", unique = true)
    private String hashMail;
    @Column(name = "masterKeySignature", nullable = false, columnDefinition = "TEXT")
    private String masterKeySignature;
    @Column(name = "repository", nullable = false, columnDefinition = "TEXT")
    private String repository;

    public StorageERM(){}

    public StorageERM(final String hashMail, final String masterKeySignature, final String repository)
    {
        this.hashMail = hashMail;
        this.masterKeySignature = masterKeySignature;
        this.repository = repository;
    }

    public String getHashmail()
    {
        String hashMail = this.hashMail;

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

    public String getMasterkeysignature()
    {
        String masterKeySignature = this.masterKeySignature;

        return masterKeySignature;
    }

    public boolean setMasterkeysignature(final String masterKeySignature)
    {
        boolean flag = true;

        if(masterKeySignature == null || masterKeySignature.equals(""))
            flag = false;
        if(flag)
            this.masterKeySignature = masterKeySignature;
            
        return flag;
    }

    public String getRepository()
    {
        String repository = this.repository;

        return repository;
    }

    public boolean setRepository(final String repository)
    {
        boolean flag = true;

        if(repository == null || repository.equals(""))
            flag = false;
        if(flag)
            this.repository = repository;
            
        return flag;
    }
}
