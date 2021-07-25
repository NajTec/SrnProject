package com.srn.secureserver.toolbox.payload;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Data extends Payload
{
    private String path;
    private String nameOfFile;
    private String hashMail;
    private String cipherData;

    private String enquirerHashMail;
    @JsonFormat(pattern="MM-dd-yyyy")
    private Date startTime;
    @JsonFormat(pattern="MM-dd-yyyy")
    private Date endTime;
    private String message;

    public Data(){}

    public Data(String hashMail, String cipherData, Date startTime,
                String path, String nameOfFile, String enquirerHashMail, 
                Date endTime, String message)
    {
        this.hashMail = hashMail;
        this.cipherData = cipherData;
        this.path = path;
        this.nameOfFile = nameOfFile;
        this.enquirerHashMail = enquirerHashMail;

        this.startTime = startTime;
        this.endTime = endTime;
        this.message = message;
    }    

    public String getHashMail() 
    {
        return hashMail;
    }

    public void setHashMail(String hashMail) 
    {
        this.hashMail = hashMail;
    }

    public String getCipherData() 
    {
        return cipherData;
    }

    public void setCipherData(String cipherData) 
    {
        this.cipherData = cipherData;
    }

    public String getPath() 
    {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNameOfFile() 
    {
        return nameOfFile;
    }

    public void setNameOfFile(String nameOfFile) 
    {
        this.nameOfFile = nameOfFile;
    }

    public String getEnquirerHashMail() 
    {
        return enquirerHashMail;
    }

    public void setEnquirerHashMail(String enquirerHashMail) 
    {
        this.enquirerHashMail = enquirerHashMail;
    }

    public Date getStartTime() 
    {
        return startTime;
    }

    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }

    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public String getMessage() 
    {
        return message;
    }

    public void setMessage(String message) 
    {
        this.message = message;
    }  
    
    
}
