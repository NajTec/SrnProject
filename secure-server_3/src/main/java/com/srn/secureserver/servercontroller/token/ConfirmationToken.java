package com.srn.secureserver.servercontroller.token;

import java.time.LocalDateTime;

import com.srn.secureserver.servercontroller.user.UserERM;

public class ConfirmationToken 
{
    private String token;
    private LocalDateTime createAt;
    private LocalDateTime expiresAt;
    private UserERM userERM;

    public ConfirmationToken(){}

    public ConfirmationToken(final String token, final LocalDateTime createAt, 
                                final LocalDateTime expiresAt, final UserERM userERM) 
    {
        this.token = token;
        this.createAt = createAt;
        this.expiresAt = expiresAt;
        this.userERM = userERM;
    }

    public String getToken() 
    {
        return token;
    }

    public void setToken(String token) 
    {
        this.token = token;
    }

    public LocalDateTime getCreateAt() 
    {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) 
    {
        this.createAt = createAt;
    }

    public LocalDateTime getExpiresAt() 
    {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) 
    {
        this.expiresAt = expiresAt;
    }
   
    public UserERM getUserERM() 
    {
        return userERM;
    }

    public void setUserERM(UserERM userERM) 
    {
        this.userERM = userERM;
    }

    
    
}
