package com.srn.tools.cipher;

import com.srn.tools.model.mHash;

public interface iHE 
{
    public mHash encrypt(final String level, final String value);
}
