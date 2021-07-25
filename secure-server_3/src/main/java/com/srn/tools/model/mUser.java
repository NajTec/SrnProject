package com.srn.tools.model;

public class mUser 
{
    private String hashMail;
    private String mail;
    private String gender;
    private String firstName;
    private String lastName;
    private String birthday;
    private String birthplace;
    private String country;
    private String state;
    private String street;
    private String activity;
    private String license;
    private String status;

    public mUser(){}

    public mUser(final String hashMail, final String mail, final String gender,
                    final String firstName, final String lastName, final String birthday,
                    final String birthplace, final String country, final String state,
                    final String street, final String activity, final String license,
                    final String status)
    {
        this.hashMail = hashMail;
        this.mail = mail;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.birthplace = birthplace;
        this.country = country;
        this.state  = state;
        this.street = street;
        this.activity = activity;
        this.license = license;
        this.status = status;
    }

    public final String getHashMail()
    {
        final String hashMail = this.hashMail;

        return hashMail;
    }

    public boolean setHashMail(final String hashMail)
    {
        boolean flag = true;

        if(hashMail == null || hashMail.equals(""))
            flag = false;
        if(flag)
            this.hashMail = hashMail;
            
        return flag;
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

    public final String getBirthday()
    {
        final String birthday = this.birthday;

        return birthday;
    }

    public boolean setBirthday(final String birthday)
    {
        boolean flag = true;

        if(birthday == null || birthday.equals(""))
            flag = false;
        if(flag)
            this.birthday = birthday;
            
        return flag;
    }

    public final String getBirthPlace()
    {
        final String birthPlace = this.birthplace;

        return birthPlace;
    }

    public boolean setBirthPlace(final String birthPlace)
    {
        boolean flag = true;

        if(birthPlace == null || birthPlace.equals(""))
            flag = false;
        if(flag)
            this.birthplace = birthPlace;
            
        return flag;
    }

    public final String getCountry()
    {
        final String country = this.country;

        return country;
    }

    public boolean setCountry(final String country)
    {
        boolean flag = true;

        if(country == null || country.equals(""))
            flag = false;
        if(flag)
            this.country = country;
            
        return flag;
    }

    public final String getState()
    {
        final String state = this.state;

        return state;
    }

    public boolean setState(final String state)
    {
        boolean flag = true;

        if(state == null || state.equals(""))
            flag = false;
        if(flag)
            this.state = state;
            
        return flag;
    }

    public final String getStreet()
    {
        final String street = this.street;

        return street;
    }

    public boolean setStreet(final String street)
    {
        boolean flag = true;

        if(street == null || street.equals(""))
            flag = false;
        if(flag)
            this.street = street;
            
        return flag;
    }

    public final String getActivity()
    {
        final String activity = this.activity;

        return activity;
    }

    public boolean setActivity(final String activity)
    {
        boolean flag = true;

        if(activity == null || activity.equals(""))
            flag = false;
        if(flag)
            this.activity = activity;
            
        return flag;
    }

    public final String getLicense()
    {
        final String license = this.license;

        return license;
    }

    public boolean setLicense(final String license)
    {
        boolean flag = true;

        if(license == null || license.equals(""))
            flag = false;
        if(flag)
            this.license = license;
            
        return flag;
    }

    public final String getStatus()
    {
        final String status = this.status;

        return status;
    }

    public boolean setStatus(final String status)
    {
        boolean flag = true;

        if(status == null || status.equals(""))
            flag = false;
        if(flag)
            this.status = status;
            
        return flag;
    }
}
