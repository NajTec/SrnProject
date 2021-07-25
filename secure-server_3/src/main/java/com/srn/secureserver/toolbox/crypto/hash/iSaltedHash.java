package com.srn.secureserver.toolbox.crypto.hash;

public interface iSaltedHash 
{
    public mHash encrypt(final String level, final String value);
}
