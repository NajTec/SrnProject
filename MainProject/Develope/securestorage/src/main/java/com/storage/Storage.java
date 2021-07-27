package com.storage;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Storage 
{
    private final String rootPath;

    public Storage(final String rootPath)
    {
        this.rootPath = rootPath;

        try 
        {
            Files.createDirectory(Paths.get(rootPath));
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    

    ///****Start setter and getter methods*******************************************************
    public final String getRootPath()
    {
        return this.rootPath;
    }
    ///****End setter and getter methods*********************************************************
}
