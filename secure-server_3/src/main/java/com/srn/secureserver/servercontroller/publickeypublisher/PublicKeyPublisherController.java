package com.srn.secureserver.servercontroller.publickeypublisher;

import com.srn.secureserver.servercontroller.global.Global;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "PublicKeyPublisher")
public class PublicKeyPublisherController 
{
    @Autowired
    public PublicKeyPublisherController()
    {

    }

    @GetMapping
    public String publishPublicKey()
    {
        return Global.rsa.publicKey();
    }
}
