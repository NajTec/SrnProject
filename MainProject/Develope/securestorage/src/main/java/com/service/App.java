package com.service;

import java.util.List;

import com.storage.Repository;
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
        Repository repository = new Repository(storage.getRootPath(), "matthias@gmail.com");
        //repository.createRepository();
        List<String> list = repository.listDirectories("directory", "Repository");
        list.forEach(System.out::println);
        repository.deleteRepository();
    }
}
