package com.srn.tools.model;

public class mShareData 
{
    private String resourceOwnerMail;
    private String resourceEnquirerMail;
    private String message;
    private String resource;
    private String request;
    private String approval;
    private String startDate;
    private String endDate;

    public mShareData(){}

    public mShareData(final String resourceOwnerMail, final String resourceEnquirerMail,
                        final String message, final String resource, final String request,
                        final String approval, final String startDate, final String endDate)
    {
        this.resourceOwnerMail = resourceOwnerMail;
        this.resourceEnquirerMail = resourceEnquirerMail;
        this.message = message;
        this.resource = resource;
        this.request = request;
        this.approval = approval;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public final String getResourceOwnerMail()
    {
        final String resourceOwnerMail = this.resourceOwnerMail;

        return resourceOwnerMail;
    }

    public boolean setResourceOwnerMail(final String resourceOwnerMail)
    {
        boolean flag = true;

        if(resourceOwnerMail == null || resourceOwnerMail.equals(""))
            flag = false;
        if(flag)
            this.resourceOwnerMail = resourceOwnerMail;
            
        return flag;
    }

    public final String getResourceEnquirerMail()
    {
        final String resourceEnquirerMail = this.resourceEnquirerMail;

        return resourceEnquirerMail;
    }

    public boolean setResourceEnquirerMail(final String resourceEnquirerMail)
    {
        boolean flag = true;

        if(resourceEnquirerMail == null || resourceEnquirerMail.equals(""))
            flag = false;
        if(flag)
            this.resourceEnquirerMail = resourceEnquirerMail;
            
        return flag;
    }

    public final String getMessage()
    {
        final String message = this.message;

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

    public final String getResource()
    {
        final String resource = this.resource;

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

    public final String getRequest()
    {
        final String request = this.request;

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

    public final String getApproval()
    {
        final String approval = this.approval;

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

    public final String getStartDate()
    {
        final String startDate = this.startDate;

        return startDate;
    }

    public boolean setStartDate(final String startDate)
    {
        boolean flag = true;

        if(startDate == null || startDate.equals(""))
            flag = false;
        if(flag)
            this.startDate = startDate;
            
        return flag;
    }

    public final String getEndDate()
    {
        final String endDate = this.endDate;

        return endDate;
    }

    public boolean setEndDate(final String endDate)
    {
        boolean flag = true;

        if(endDate == null || endDate.equals(""))
            flag = false;
        if(flag)
            this.endDate = endDate;
            
        return flag;
    }
}
