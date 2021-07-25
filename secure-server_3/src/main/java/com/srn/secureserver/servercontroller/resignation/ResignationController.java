package com.srn.secureserver.servercontroller.resignation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "resignation")
public class ResignationController 
{
    private final ResignationService resignationService;

    @Autowired
    public ResignationController(final ResignationService resignationService)
    {
        this.resignationService = resignationService;
    }

    @PostMapping(path = "resignation")
    public String register(@RequestBody final ResignationRequest request)
    {
        this.resignationService.resign(request.getHashmail());

        final String flag = "User with hash-email: " + request.getHashmail() + " deleted!";

        return flag;
    }
}
