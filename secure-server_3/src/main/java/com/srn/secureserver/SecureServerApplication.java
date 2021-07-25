package com.srn.secureserver;

import com.srn.secureserver.servercontroller.global.Global;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecureServerApplication 
{	
	public static void main(String[] args) 
	{
		System.out.println(Global.global);
		SpringApplication.run(SecureServerApplication.class, args);
	}


}