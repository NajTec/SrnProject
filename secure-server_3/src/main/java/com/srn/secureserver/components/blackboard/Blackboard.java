package com.srn.secureserver.components.blackboard;

import java.sql.Date;

public class Blackboard 
{
    private String ownerHashMail;
    private String enquirerHashMail;
    private String message;
    private String resource;
    private String request;
    private String approval;
    private Date startDate;
    private Date endDate;

    public Blackboard()
    {

    }

    public Blackboard(final String ownerHashMail, final String enquirerHashMail, final String message,
                        final String resource, final String request, final String approval, 
                        final Date startDate, final Date endDate)
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

    public String getOwnerHashMail() 
    {
        return ownerHashMail;
    }

    public void setOwnerHashMail(String ownerHashMail) 
    {
        this.ownerHashMail = ownerHashMail;
    }

    public String getEnquirerHashMail() 
    {
        return enquirerHashMail;
    }

    public void setEnquirerHashMail(String enquirerHashMail) 
    {
        this.enquirerHashMail = enquirerHashMail;
    }

    public String getMessage() 
    {
        return message;
    }

    public void setMessage(String message) 
    {
        this.message = message;
    }

    public String getResource() 
    {
        return resource;
    }

    public void setResource(String resource) 
    {
        this.resource = resource;
    }

    public String getRequest() 
    {
        return request;
    }

    public void setRequest(String request) 
    {
        this.request = request;
    }

    public String getApproval() 
    {
        return approval;
    }

    public void setApproval(String approval) 
    {
        this.approval = approval;
    }

    public Date getStartDate() 
    {
        return startDate;
    }

    public void setStartDate(Date startDate) 
    {
        this.startDate = startDate;
    }

    public Date getEndDate() 
    {
        return endDate;
    }

    public void setEndDate(Date endDate) 
    {
        this.endDate = endDate;
    }

    

}
