package com.srn.tools.model;

public class mPassword 
{
    private String hashMail;
    private String salt;
    private String hashPassword;

    public mPassword(){}

    public mPassword(final String hashMail, final String salt, final String hashPassword)
    {
        this.hashMail = hashMail;
        this.salt = salt;
        this.hashPassword = hashPassword;
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

    public final String getSalt()
    {
        final String salt = this.salt;

        return salt;
    }

    public boolean setSalt(final String salt)
    {
        boolean flag = true;

        if(salt == null || salt.equals(""))
            flag = false;
        if(flag)
            this.salt = salt;
            
        return flag;
    }

    public final String getHashPassword()
    {
        final String hashPassword = this.hashPassword;

        return hashPassword;
    }

    public boolean setHashPassword(final String hashPassword)
    {
        boolean flag = true;

        if(hashPassword == null || hashPassword.equals(""))
            flag = false;
        if(flag)
            this.hashPassword = hashPassword;
            
        return flag;
    }
}
