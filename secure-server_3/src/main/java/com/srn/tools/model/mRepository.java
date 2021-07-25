package com.srn.tools.model;

public class mRepository 
{
    private String hashMail;
    private String masterKeySignature;
    private String repositoryPath;
    private String repository;

    public mRepository(){}

    public mRepository(final String hashMail, final String masterKeySignature, 
                        final String repositoryPath, final String repository)
    {
        this.hashMail = hashMail;
        this.masterKeySignature = masterKeySignature;
        this.repositoryPath = repositoryPath;
        this.repository = repository;
    }

    public final String getHashMail()
    {
        final String hashMail = this.hashMail;

        return hashMail;
    }

    public boolean setHashMail(final String hashMail)
    {
        boolean flag = true;

        if(hashMail == null || hashMail.equals(""))
            flag = false;
        if(flag)
            this.hashMail = hashMail;
            
        return flag;
    }

    public final String getMasterKeySignature()
    {
        final String masterKeySignature = this.masterKeySignature;

        return masterKeySignature;
    }

    public boolean setMasterKeySignature(final String masterKeySignature)
    {
        boolean flag = true;

        if(masterKeySignature == null || masterKeySignature.equals(""))
            flag = false;
        if(flag)
            this.masterKeySignature = masterKeySignature;
            
        return flag;
    }

    public final String getRepositoryPath()
    {
        final String repositoryPath = this.repositoryPath;

        return repositoryPath;
    }

    public boolean setRepositoryPath(final String repositoryPath)
    {
        boolean flag = true;

        if(repositoryPath == null || repositoryPath.equals(""))
            flag = false;
        if(flag)
            this.repositoryPath = repositoryPath;
            
        return flag;
    }

    public final String getRepository()
    {
        final String repository = this.repository;

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
