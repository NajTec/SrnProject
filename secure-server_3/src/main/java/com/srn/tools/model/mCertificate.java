package com.srn.tools.model;

public class mCertificate 
{
    private String mail;
    private String gender;
    private String firstName;
    private String lastName;
    private String address;
    private String signature;

    public mCertificate(){}

    public mCertificate(final String mail, final String gender, final String firstName,
                            final String lastName, final String address, final String signature)
    {
        this.mail = mail;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.signature = signature;
    }    

    public final String getMail()
    {
        final String mail = this.mail;

        return mail;
    }

    public boolean setMail(final String mail)
    {
        boolean flag = true;

        if(mail == null || mail.equals(""))
            flag = false;
        if(flag)
            this.mail = mail;
            
        return flag;
    }

    public final String getGender()
    {
        final String gender = this.gender;

        return gender;
    }

    public boolean setGender(final String gender)
    {
        boolean flag = true;

        if(gender == null || gender.equals(""))
            flag = false;
        if(flag)
            this.gender = gender;
            
        return flag;
    }

    public final String getFirstName()
    {
        final String firstName = this.firstName;

        return firstName;
    }

    public boolean setFirstName(final String firstName)
    {
        boolean flag = true;

        if(firstName == null || firstName.equals(""))
            flag = false;
        if(flag)
            this.firstName = firstName;
            
        return flag;
    }

    public final String getLastName()
    {
        final String lastName = this.lastName;

        return lastName;
    }

    public boolean setLastName(final String lastName)
    {
        boolean flag = true;

        if(lastName == null || lastName.equals(""))
            flag = false;
        if(flag)
            this.lastName = lastName;
            
        return flag;
    }

    public final String getAddress()
    {
        final String address = this.address;

        return address;
    }

    public boolean setAddress(final String address)
    {
        boolean flag = true;

        if(address == null || address.equals(""))
            flag = false;
        if(flag)
            this.address = address;
            
        return flag;
    }

    public final String getSignature()
    {
        final String signature = this.signature;

        return signature;
    }

    public boolean setSignature(final String signature)
    {
        boolean flag = true;

        if(signature == null || signature.equals(""))
            flag = false;
        if(flag)
            this.signature = signature;

        return flag;
    }
}
