package com.srn.secureserver.components.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.ToString;

@Entity(name = "Customer")
@Table(name = "customer")
@ToString
public class CustomerERM
{
    @Id
    @SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")
    @Column(name = "id", nullable = false, columnDefinition = "TEXT", unique = true)
    private Long id;
    @Column(name = "email", nullable = false, columnDefinition = "TEXT", unique = true)
    private String email;
    @Column(name = "hashMail", nullable = false, columnDefinition = "TEXT", unique = true)
    private String hashMail;
    @Column(name = "gender", nullable = false, columnDefinition = "TEXT")
    private String gender;
    @Column(name = "firstName", nullable = false, columnDefinition = "TEXT")
    private String firstName;
    @Column(name = "lastName", nullable = false, columnDefinition = "TEXT")
    private String lastName;
    @Column(name = "birthday", nullable = false, columnDefinition = "TEXT")
    private String birthday;
    @Column(name = "birthplace", nullable = false, columnDefinition = "TEXT")
    private String birthplace;
    @Column(name = "country", nullable = false, columnDefinition = "TEXT")
    private String country;
    @Column(name = "state", nullable = false, columnDefinition = "TEXT")
    private String state;
    @Column(name = "street", nullable = false, columnDefinition = "TEXT")
    private String street;    
    
    public CustomerERM(){}

    public CustomerERM(final String hashMail, final String email, final String gender,
                            final String firstName, final String lastName, final String birthday,
                            final String birthplace, final String country, final String state,
                            final String street)
    {
        this.hashMail = hashMail;
        this.email = email;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.birthplace = birthplace;
        this.country = country;
        this.state  = state;
        this.street = street;
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
        String hashMail = this.hashMail;

        return hashMail;
    }

    public boolean setHashmail(final String hashMail)
    {
        boolean flag = true;

        if(hashMail == null || hashMail.equals(""))
            flag = false;
        if(flag)
            this.hashMail = hashMail;
            
        return flag;
    }

    public String getEmail()
    {
        String email = this.email;

        return email;
    }

    public boolean setEmail(final String email)
    {
        boolean flag = true;

        if(email == null || email.equals(""))
            flag = false;
        if(flag)
            this.email = email;
            
        return flag;
    }

    public String getGender()
    {
        String gender = this.gender;

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

    public String getFirstname()
    {
        String firstName = this.firstName;

        return firstName;
    }

    public boolean setFirstname(final String firstName)
    {
        boolean flag = true;

        if(firstName == null || firstName.equals(""))
            flag = false;
        if(flag)
            this.firstName = firstName;
            
        return flag;
    }

    public String getLastname()
    {
        String lastName = this.lastName;

        return lastName;
    }

    public boolean setLastname(final String lastName)
    {
        boolean flag = true;

        if(lastName == null || lastName.equals(""))
            flag = false;
        if(flag)
            this.lastName = lastName;
            
        return flag;
    }

    public String getBirthday()
    {
        String birthday = this.birthday;

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

    public String getBirthplace()
    {
        String birthplace = this.birthplace;

        return birthplace;
    }

    public boolean setBirthplace(final String birthplace)
    {
        boolean flag = true;

        if(birthplace == null || birthplace.equals(""))
            flag = false;
        if(flag)
            this.birthplace = birthplace;
            
        return flag;
    }

    public String getCountry()
    {
        String country = this.country;

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

    public String getState()
    {
        String state = this.state;

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

    public String getStreet()
    {
        String street = this.street;

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
}
