package com.service;

import java.util.List;

import com.storage.Storage;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Max.: " + Runtime.getRuntime().maxMemory()/(1024*1024*1024) + "GB");
        System.out.println("Total: " + Runtime.getRuntime().totalMemory()/(1024*1024) + "MB");
        System.out.println("Used: " + (Runtime.getRuntime().freeMemory()-Runtime.getRuntime().freeMemory())/(1024*1024) + "MB");
        System.out.println("Free: " + Runtime.getRuntime().freeMemory()/(1024*1024) + "MB");


        Storage storage = new Storage("storage");
        //storage.createRepository("matthias@gmail.com");
        List<String> list = storage.listDirectories("directory", "matthias@gmail.com", "Repository");
        list.forEach(System.out::println);
        storage.deleteRepository("matthias@gmail.com");
    }
}
