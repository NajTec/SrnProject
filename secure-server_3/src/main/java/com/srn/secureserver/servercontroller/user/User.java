package com.srn.secureserver.servercontroller.user;

public class User 
{
    private String hashMail;
    private UserRole userRole;
    private Boolean locked;
    private Boolean enabled;


    public User(final String hashMail, final UserRole userRole, final Boolean locked, 
                final Boolean enabled) 
    {
        this.hashMail = hashMail;
        this.userRole = userRole;
        this.locked = locked;
        this.enabled = enabled;
    }

    public String getHashMail() 
    {
        return hashMail;
    }


    public void setHashMail(String hashMail) 
    {
        this.hashMail = hashMail;
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
