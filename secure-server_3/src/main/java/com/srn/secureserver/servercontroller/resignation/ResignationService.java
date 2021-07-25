package com.srn.secureserver.servercontroller.resignation;

import com.srn.secureserver.components.blackboard.BlackboardService;
import com.srn.secureserver.components.customer.CustomerService;
import com.srn.secureserver.components.password.PasswordService;
import com.srn.secureserver.components.signature.SignatureService;
import com.srn.secureserver.components.storage.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResignationService 
{
    private final CustomerService customerService;
    private final PasswordService passwordService;
    private final SignatureService signatureService;
    private final StorageService storageService;
    private final BlackboardService blackboardService;

    @Autowired
    public ResignationService(final CustomerService customerService, final PasswordService passwordService, 
                                final SignatureService signatureService, final StorageService storageService,
                                final BlackboardService blackboardService)
    {
        this.customerService = customerService;
        this.passwordService = passwordService;
        this.signatureService = signatureService;
        this.storageService = storageService;
        this.blackboardService = blackboardService;
    }

    public String resign(final String hashMail)
    {
        this.blackboardService.cleanUp(hashMail);
        this.storageService.cleanUp(hashMail);
        this.signatureService.cleanUp(hashMail);
        this.passwordService.cleanUp(hashMail);
        this.customerService.cleanUp(hashMail);

        final String flag = "User with hahs-email: " + hashMail + " deleted!";

        return flag;
    }
}
