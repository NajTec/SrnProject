package com.srn.secureserver.servercontroller.user;

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

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "Users")
@Table(name = "users")
@EqualsAndHashCode
@ToString
public class UserERM implements UserDetails
{
    @Id
    @SequenceGenerator(name = "users_sequence", sequenceName = "users_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sequence")
    @Column(name = "id", nullable = false, columnDefinition = "TEXT", unique = true)
    private Long id;
    @Column(name = "email", nullable = false, columnDefinition = "TEXT", unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "userRole", nullable = false, columnDefinition = "TEXT")
    private UserRole userRole;
    @Column(name = "locked", nullable = false)
    private Boolean locked = false;
    @Column(name = "enabled", nullable = false)
    private Boolean enabled = false;

    public UserERM(){}

    public UserERM(final String email, final UserRole userRole, final Boolean locked, 
                    final Boolean enabled) 
    {
        this.email = email;
        this.userRole = userRole;
        this.locked = locked;
        this.enabled = enabled;
    }

    public UserERM(final Long id, final String email, final UserRole userRole, final Boolean locked, 
                    final Boolean enabled) 
    {
        this.id = id;
        this.email = email;
        this.userRole = userRole;
        this.locked = locked;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() 
    {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        Collection<? extends GrantedAuthority> collection = Collections.singleton(authority);

        return collection;
    }

    @Override
    public String getPassword() 
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getUsername() 
    {
        return this.email;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public UserRole getUserRole() 
    {
        return userRole;
    }

    public void setUserRole(UserRole userRole) 
    {
        this.userRole = userRole;
    }

    public Boolean getLocked() 
    {
        return locked;
    }

    public void setLocked(Boolean locked) 
    {
        this.locked = locked;
    }

    public Boolean getEnabled() 
    {
        return enabled;
    }

    public void setEnabled(Boolean enabled) 
    {
        this.enabled = enabled;
    }

    
    
}
