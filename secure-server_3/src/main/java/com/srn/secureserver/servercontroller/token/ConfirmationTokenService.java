package com.srn.secureserver.servercontroller.token;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenService 
{
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    public ConfirmationTokenService(final ConfirmationTokenRepository confirmationTokenRepository)
    {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    public String save(final ConfirmationToken token)
    {
        ConfirmationTokenERM confirmationTokenERM = new ConfirmationTokenERM(token.getUserERM(), token.getToken(), 
                                                                                token.getCreateAt(), token.getExpiresAt());

        this.confirmationTokenRepository.save(confirmationTokenERM);

        final String flag = "Token: " + token + " saved!";

        return flag;
    }

    @Transactional
    public String confirm(final String token)
    {
        ConfirmationTokenERM confirmationTokenERM = this.confirmationTokenRepository.findByToken(token)
                                                                                        .orElseThrow(() ->
                                                                                            new IllegalStateException("Token: " + token + " not found!"));
        
        if(confirmationTokenERM.getConfirmedAt() != null)
            throw new IllegalStateException("Email already confirmed!");

        LocalDateTime expiredAt = confirmationTokenERM.getConfirmedAt();

        if(expiredAt.isBefore(LocalDateTime.now()))
            throw new IllegalStateException("Token: " + token + " expired!");

        //public int setConfirmedAt(String token) {
        this.confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
        final String email = confirmationTokenERM.getUserERM().getEmail();

        return email;
    }


}
