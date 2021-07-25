package com.srn.secureserver.components.signature;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/signature")
public class SignatureController 
{
    private final SignatureService signatureService;

    @Autowired
    public SignatureController(final SignatureService signatureService)
    {
        this.signatureService = signatureService;
    }

    @GetMapping
    public List<SignatureERM> signatures()
    {
        return this.signatureService.signatures();
    }
}
