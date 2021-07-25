package com.srn.secureserver.components.customer;


import com.fasterxml.jackson.databind.JsonNode;
import com.srn.secureserver.servercontroller.registration.RegistrationRequest;

import lombok.ToString;

@ToString
public class Customer 
{
    private String mail;
    private String hashMail;
    private String gender;
    private String firstName;
    private String lastName;
    private String birthday;
    private String birthplace;
    private String country;
    private String state;
    private String street;
    private String password;
    private String publicKey;
    private String masterKey;

    public Customer(){}

    public Customer(final RegistrationRequest request)
    {
        this.mail = request.getEmail();
        this.gender = request.getGender();
        this.firstName = request.getFirstname();
        this.lastName = request.getLastname();
        this.birthday = request.getBirthday();
        this.birthplace = request.getBirthplace();
        this.country = request.getCountry();
        this.state = request.getState();
        this.street = request.getStreet();
        this.password = request.getPassword();
        this.publicKey = request.getPublickey();
        this.masterKey = request.getMasterkey();
    }

    public Customer(final JsonNode json)
    {
        this.mail = json.path("email").textValue(); 
        this.gender = json.path("gender").textValue();
        this.firstName = json.path("firstname").textValue();
        this.lastName = json.path("lastname").textValue(); 
        this.birthday = json.path("birthday").textValue(); 
        this.birthplace = json.path("birthplace").textValue(); 
        this.country = json.path("country").textValue(); 
        this.state = json.path("state").textValue(); 
        this.street = json.path("street").textValue(); 
        this.password = json.path("password").textValue(); 
        this.publicKey = json.path("publickey").textValue(); 
        this.masterKey = json.path("masterkey").textValue();
    }

    public Customer(final String mail, final String hashMail, final String gender,
                    final String firstName, final String lastName, final String birthday,
                    final String birthplace, final String country, final String state,
                    final String street, final String password, final String publicKey,
                    final String masterKey)
    {
        this.mail = mail;
        this.hashMail = hashMail;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.birthplace = birthplace;
        this.country = country;
        this.state  = state;
        this.street = street;
        this.password = password;
        this.publicKey = publicKey;
        this.masterKey = masterKey;
    }

    public String getEmail()
    {
        return this.mail;
    }

    public void setEmail(final String mail)
    {
        this.mail = mail;
    }

    public String getHashmmail()
    {
        return this.hashMail;
    }

    public void setHashmail(final String hashMail)
    {
        this.hashMail = hashMail;
    }

    public String getGender()
    {
        return this.gender;
    }

    public void setGender(final String gender)
    {
        this.gender = gender;
    }

    public String getFirstname()
    {
        return this.firstName;
    }

    public void setFirstname(final String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastname()
    {
        return this.lastName;
    }

    public void setLastname(final String lastName)
    {
        this.lastName = lastName;
    }

    public String getBirthday()
    {
        return this.birthday;
    }

    public void setBirthday(final String birthday)
    {
        this.birthday = birthday;
    }

    public String getBirthplace()
    {
        return this.birthplace;
    }

    public void setBirthplace(final String birthPlace)
    {
        this.birthplace = birthPlace;
    }

    public String getCountry()
    {
        return this.country;
    }

    public void setCountry(final String country)
    {
        this.country = country;
    }

    public String getState()
    {
        return this.state;
    }

    public void setState(final String state)
    {
        this.state = state;
    }

    public String getStreet()
    {
        return this.street;
    }

    public void setStreet(final String street)
    {
        this.street = street;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(final String password)
    {
        this.password = password;
    }

    public String getPublickey()
    {
        return this.publicKey;
    }

    public void setPublickey(final String publicKey)
    {
        this.publicKey = publicKey;
    }

    public String getMasterkey()
    {
        return this.masterKey;
    }

    public void setMasterkey(final String masterKey)
    {
        this.masterKey = masterKey;
    }
}
