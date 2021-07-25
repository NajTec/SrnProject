package com.srn.secureserver.servercontroller.registration;

import com.srn.secureserver.components.customer.Customer;
import com.srn.secureserver.components.customer.CustomerService;
import com.srn.secureserver.components.password.Password;
import com.srn.secureserver.components.password.PasswordService;
import com.srn.secureserver.components.signature.Signature;
import com.srn.secureserver.components.signature.SignatureService;
import com.srn.secureserver.components.storage.Storage;
import com.srn.secureserver.components.storage.StorageService;
import com.srn.secureserver.servercontroller.email.EmailSender;
import com.srn.secureserver.servercontroller.email.EmailTemplate;
import com.srn.secureserver.servercontroller.token.ConfirmationTokenService;
import com.srn.secureserver.servercontroller.user.User;
import com.srn.secureserver.servercontroller.user.UserRole;
import com.srn.secureserver.servercontroller.user.UserService;
import com.srn.secureserver.toolbox.validator.email.EmailValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService 
{
    private final CustomerService customerService;
    private final PasswordService passwordService;
    private final SignatureService signatureService;
    private final StorageService storageService;
    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;

    private final EmailSender emailSender;
    private final EmailValidator emailValidator;

    @Autowired
    public RegistrationService(final CustomerService customerService, final PasswordService passwordService, 
                                final SignatureService signatureService, final StorageService storageService, 
                                final EmailValidator emailValidator, final UserService userService,
                                final ConfirmationTokenService confirmationTokenService,
                                final EmailSender emailSender)
    {
        this.customerService = customerService;
        this.passwordService = passwordService;
        this.signatureService = signatureService;
        this.storageService = storageService;
        this.userService = userService;
        this.confirmationTokenService = confirmationTokenService;

        this.emailSender = emailSender;
        this.emailValidator = emailValidator;
    }

    public String register(final RegistrationRequest request)
    {
        boolean isValideMail = this.emailValidator.test(request.getEmail());
        if(!isValideMail)
            throw new IllegalStateException("Email: " + request.getEmail() + " is not valid!");

        final Customer customer = new Customer(request);
        final boolean customerIsPresent = this.customerService.loadCustomerByEmail(customer.getEmail());
        if(customerIsPresent)
            throw new IllegalStateException("Email : " + customer.getEmail() + " is already taken!");
        final String hashMailC = this.customerService.signUp(customer);

        final User user = new User(hashMailC, UserRole.USER, /*"locked"*/true, /*"enabled"*/true);
        final boolean userIsPresent = this.userService.loadUserByHashMail(customer.getEmail());
        if(userIsPresent)
            throw new IllegalStateException("Hash-email : " + hashMailC + " is already taken!");
        final String token = this.userService.signUp(user);

        final Password password = new Password(hashMailC, request);
        final boolean passwordIsPresent = this.passwordService.loadPasswordByHashMmail(hashMailC);
        if(passwordIsPresent)
            throw new IllegalStateException("Hash-email : " + hashMailC + " is already taken!");
        final String hashMailP = this.passwordService.signUp(password);

        final Signature signature = new Signature(hashMailP, request);
        final boolean signatureIsPresent = this.signatureService.loadSignatureByHashMmail(hashMailP);
        if(signatureIsPresent)
            throw new IllegalStateException("Hash-email : " + hashMailP + " is already taken!");
        final String hashMailS = this.signatureService.signUp(signature);

        final Storage storage = new Storage(hashMailS, request);
        final boolean storageIsPresent = this.storageService.loadStorageByHashMmail(hashMailS);
        if(storageIsPresent)
            throw new IllegalStateException("Hash-email : " + hashMailS + " is already taken!");
        final String hashMailSt = this.storageService.signUp(storage);


       final String link = "http://localhost:8080/registration/confirm?token=" + token;
       this.emailSender.send(request.getEmail(), 
                            EmailTemplate.buildEmail(
                                request.getFirstname() + " " + 
                                    request.getLastname(), link));


        final String flag = "Customer with hash-email: " + hashMailSt + " and token " + 
                                token + " 30 minutes to confirm in email";

        return flag;
    }

    public String confirmToken(final String token)
    {
        final String email = this.confirmationTokenService.confirm(token);
        this.userService.enable(email);
        final String flag = "Token: " + token + " confirmed!";

        return flag;
    }
}
