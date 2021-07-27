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
        Storage storage = new Storage("storage");
        //storage.createRepository("matthias@gmail.com");
        List<String> list = storage.listDirectories("directory", "matthias@gmail.com", "Storage");
        list.forEach(System.out::println);
        storage.deleteRepository("matthias@gmail.com");
    }
}
