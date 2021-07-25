package com.srn.secureserver.servercontroller.global;

import com.srn.secureserver.toolbox.crypto.asymmetric.RSA;
import com.srn.secureserver.toolbox.repository.Storage;

public class Global 
{
    public static final String global = "Start create initialization!";
    public static final RSA rsa = new RSA(2048);    
    public static final Storage storage = new Storage("secure_server_storage");

}
