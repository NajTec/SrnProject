package com.srn.secureserver.components.password;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.srn.secureserver.servercontroller.user.UserRole;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.ToString;

@Entity(name = "Password")
@Table(name = "password")
@ToString
public class PasswordERM implements UserDetails
{
    @Id
    @SequenceGenerator(name = "password_sequence", sequenceName = "password_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "password_sequence")
    @Column(name = "id", nullable = false, columnDefinition = "TEXT", unique = true)
    private Long id;
    @Column(name = "hashMail", nullable = false, columnDefinition = "TEXT", unique = true)
    private String hashMail;
    @Column(name = "hashPassword", nullable = false, columnDefinition = "TEXT")
    private String hashPassword;  
    @Column(name = "userRole", nullable = false, columnDefinition = "TEXT")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @Column(name = "locked", nullable = false, columnDefinition = "boolean")
    private Boolean locked;
    @Column(name = "enabled", nullable = false, columnDefinition = "boolean")
    private Boolean enabled;

    public PasswordERM(){}

    public PasswordERM(final String hashMail, final String hashPassword)
    {
        this.hashMail = hashMail;
        this.hashPassword = hashPassword;
    }

    public Long getId()
    {
        Long id = this.id;

        return id;
    }

    public boolean setId(final Long id)
    {
        boolean flag = true;

        if(id == null)
            flag = false;
        if(flag)
            this.id = id;
            
        return flag;
    }

    public String getHashmail()
    {
        String email = this.hashMail;

        return email;
    }

    public boolean setHashmail(final String email)
    {
        boolean flag = true;

        if(email == null || email.equals(""))
            flag = false;
        if(flag)
            this.hashMail = email;
            
        return flag;
    }
    
    public String getHashPassword()
    {
        String hashPassword = this.hashPassword;

        return hashPassword;
    }

    public boolean setHashPassword(final String hashPassword)
    {
        boolean flag = true;

        if(hashPassword == null || hashPassword.equals(""))
            flag = false;
        if(flag)
            this.hashPassword = hashPassword;
            
        return flag;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() 
    {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userRole.name());
       
        return Collections.singletonList(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() 
    {
        return this.hashPassword;
    }

    @Override
    public String getUsername() 
    {
        return this.hashMail;
    }

    @Override
    public boolean isAccountNonExpired() 
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() 
    {
        return !this.locked;
    }

    @Override
    public boolean isCredentialsNonExpired() 
    {
        return true;
    }

    @Override
    public boolean isEnabled() 
    {
        return this.enabled;
    }

    public UserRole getUserRole() 
    {
        return userRole;
    }

    public void setUserRole(final UserRole userRole) 
    {
        this.userRole = userRole;
    }

    public Boolean getLocked() 
    {
        return locked;
    }

    public void setLocked(final Boolean locked) 
    {
        this.locked = locked;
    }

    public Boolean getEnabled() 
    {
        return enabled;
    }

    public void setEnabled(final Boolean enabled) 
    {
        this.enabled = enabled;
    }  
}
