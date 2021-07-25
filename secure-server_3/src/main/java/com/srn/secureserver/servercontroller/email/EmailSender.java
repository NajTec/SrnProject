package com.srn.secureserver.servercontroller.email;

public interface EmailSender 
{
    void send(String to, String email);
}