package com.srn.secureserver.servercontroller.registration;

import com.srn.secureserver.servercontroller.global.Global;
import com.srn.secureserver.servercontroller.user.UserService;
import com.srn.secureserver.toolbox.crypto.asymmetric.iRSA;
import com.srn.secureserver.toolbox.json.iJson;
import com.srn.secureserver.toolbox.payload.Payload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "registration")
public class RegistrationController 
{
    private final iJson json;
    private final iRSA rsa;

    private final RegistrationService registrationService;
    private final UserService userService;

    @Autowired
    public RegistrationController(final iJson json, final iRSA rsa, 
                                    final RegistrationService registrationService, final UserService userService)
    {
        this.json = json;
        this.rsa = rsa;

        this.registrationService = registrationService;
        this.userService = userService;
    }

    @PostMapping(path = "reg")
    public String register(@RequestBody final Payload payload)
    {
        final String data = this.rsa.decrypt("private", Global.rsa.privateKey(), payload.getPayload());
        final RegistrationRequest request = new RegistrationRequest(this.json.getJson(data));
        final String response = this.registrationService.register(request);

        return response;
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") final String token)
    {
        final String hashMail = this.registrationService.confirmToken(token);
        this.userService.enable(hashMail);

        final String flag = "User enabled!";

        return flag;
    }
}
