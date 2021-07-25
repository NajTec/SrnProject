package com.srn.secureserver.servercontroller.token;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.srn.secureserver.servercontroller.user.UserERM;

import lombok.ToString;

@Entity(name = "Confirmationtoken")
@Table(name = "confirmationtoken")
@ToString
public class ConfirmationTokenERM 
{
    @Id
    @SequenceGenerator(name = "confirmationtoken_sequence", sequenceName = "confirmationtoken_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "confirmationtoken_sequence")
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(name = "token", nullable = false, columnDefinition = "TEXT", unique = true)
    private String token;
    @Column(name = "createAt", nullable = false, columnDefinition = "Date")
    private LocalDateTime createAt;
    @Column(name = "expiredAt", nullable = false, columnDefinition = "Date")
    private LocalDateTime expiresAt;
    //@Column(name = "confirmedAt", nullable = false, columnDefinition = "Date")
    private LocalDateTime confirmedAt;
    @ManyToOne // one user may have many tokens
    @JoinColumn(name = "user_id", nullable = false)
    private UserERM userERM;

    public ConfirmationTokenERM(){}


    public ConfirmationTokenERM(final UserERM userERM, final String token, final LocalDateTime createAt, 
                                final LocalDateTime expiresAt, final LocalDateTime confirmedAt) 
    {
        this.userERM = userERM;

        this.token = token;
        this.createAt = createAt;
        this.expiresAt = expiresAt;
        this.confirmedAt = confirmedAt;
    }

    public ConfirmationTokenERM(final UserERM userERM, final String token, final LocalDateTime createAt, 
                                final LocalDateTime expiresAt) 
    {
        this.userERM = userERM;

        this.token = token;
        this.createAt = createAt;
        this.expiresAt = expiresAt;
    }

    public Long getId() 
    {
        return id;
    }

    public void setId(final Long id) 
    {
        this.id = id;
    }

    public String getToken() 
    {
        return token;
    }

    public void setToken(final String token) 
    {
        this.token = token;
    }

    public LocalDateTime getCreateAt() 
    {
        return createAt;
    }

    public void setCreateAt(final LocalDateTime createAt) 
    {
        this.createAt = createAt;
    }

    public LocalDateTime getExpiresAt() 
    {
        return expiresAt;
    }

    public void setExpiresAt(final LocalDateTime expiredAt)
    {
        this.expiresAt = expiredAt;
    }

    public LocalDateTime getConfirmedAt() 
    {
        return confirmedAt;
    }

    public void setConfirmedAt(final LocalDateTime confirmedAt) 
    {
        this.confirmedAt = confirmedAt;
    }

    public UserERM getUserERM() 
    {
        return userERM;
    }

    public void setUserERM(final UserERM userERM) 
    {
        this.userERM = userERM;
    }

    


    
    
}
