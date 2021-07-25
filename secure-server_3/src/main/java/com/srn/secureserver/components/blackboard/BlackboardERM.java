package com.srn.secureserver.components.blackboard;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.ToString;

@Entity(name = "Blackboard")
@Table(name = "blackboard")
@ToString
public class BlackboardERM 
{
    @Id
    @SequenceGenerator(name = "blackboard_sequence", sequenceName = "blackboard_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blackboard_sequence")
    @Column(name = "id", nullable = false, columnDefinition = "TEXT", unique = true)
    private Long id;
    @Column(name = "ownerHashEmail", nullable = false, columnDefinition = "TEXT")
    private String ownerHashMail;
    @Column(name = "enquirerHashMail", nullable = false, columnDefinition = "TEXT")
    private String enquirerHashMail;
    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    private String message;
    @Column(name = "resource", nullable = false, columnDefinition = "TEXT")
    private String resource;
    @Column(name = "request", nullable = false, columnDefinition = "TEXT")
    private String request;
    @Column(name = "approval", nullable = false, columnDefinition = "TEXT")
    private String approval;
    @Column(name = "startDate", nullable = false, columnDefinition = "TEXT")
    private Date startDate;
    @Column(name = "endDate", nullable = false, columnDefinition = "TEXT")
    private Date endDate;

    public BlackboardERM(){}

    public BlackboardERM(final String ownerHashMail, final String enquirerHashMail,
                            final String message, final String resource, final String request,
                            final String approval, final Date startDate, final Date endDate)
    {
        this.ownerHashMail = ownerHashMail;
        this.enquirerHashMail = enquirerHashMail;
        this.message = message;
        this.resource = resource;
        this.request = request;
        this.approval = approval;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String getOwnerHashMail()
    {
        String ownerHashMail = this.ownerHashMail;

        return ownerHashMail;
    }

    public boolean setOwnerHashMail(final String ownerHashMail)
    {
        boolean flag = true;

        if(ownerHashMail == null || ownerHashMail.equals(""))
            flag = false;
        if(flag)
            this.ownerHashMail = ownerHashMail;
            
        return flag;
    }

    public String getEnquirerHashMail()
    {
        String enquirerHashMail = this.enquirerHashMail;

        return enquirerHashMail;
    }

    public boolean setEnquirerHashMail(final String enquirerHashMail)
    {
        boolean flag = true;

        if(enquirerHashMail == null || enquirerHashMail.equals(""))
            flag = false;
        if(flag)
            this.enquirerHashMail = enquirerHashMail;
            
        return flag;
    }

    public String getMessage()
    {
        String message = this.message;

        return message;
    }

    public boolean setMessage(final String message)
    {
        boolean flag = true;

        if(message == null || message.equals(""))
            flag = false;
        if(flag)
            this.message = message;
            
        return flag;
    }

    public String getResource()
    {
        String resource = this.resource;

        return resource;
    }

    public boolean setResource(final String resource)
    {
        boolean flag = true;

        if(resource == null || resource.equals(""))
            flag = false;
        if(flag)
            this.resource = resource;
            
        return flag;
    }

    public String getRequest()
    {
        String request = this.request;

        return request;
    }

    public boolean setRequest(final String request)
    {
        boolean flag = true;

        if(request == null || request.equals(""))
            flag = false;
        if(flag)
            this.request = request;
            
        return flag;
    }

    public String getApproval()
    {
        String approval = this.approval;

        return approval;
    }

    public boolean setApproval(final String approval)
    {
        boolean flag = true;

        if(approval == null || approval.equals(""))
            flag = false;
        if(flag)
            this.approval = approval;
            
        return flag;
    }

    public Date getStartdate()
    {
        Date startDate = this.startDate;

        return startDate;
    }

    public boolean setStartdate(final Date startDate)
    {
        boolean flag = true;

        if(startDate == null || startDate.equals(""))
            flag = false;
        if(flag)
            this.startDate = startDate;
            
        return flag;
    }

    public Date getEnddate()
    {
        Date endDate = this.endDate;

        return endDate;
    }

    public boolean setEnddate(final Date endDate)
    {
        boolean flag = true;

        if(endDate == null || endDate.equals(""))
            flag = false;
        if(flag)
            this.endDate = endDate;
            
        return flag;
    }
}
