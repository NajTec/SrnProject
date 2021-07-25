package com.srn.secureserver.toolbox.crypto.hash;

public class mHash 
{
    private final String salt;
    private final String hash;
    
    public mHash(final String hash, final String salt)
    {
        this.hash = hash;
        this.salt = salt;
    }

    public final String getHash()
    {
        String hash = null;

        if(this.hash != null)
            hash = this.hash;

        return hash;
    }

    public final String getSalt()
    {
        String salt = null;

        if(this.salt != null)
            salt = this.salt;

        return salt;
    }
}
