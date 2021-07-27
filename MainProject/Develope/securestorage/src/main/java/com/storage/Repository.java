package com.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.util.FileSystemUtils;

public class Repository 
{
    final String rootPath;
    final String email;
    final String rootRepository;

    public Repository(final String root, final String email)
    {
        this.rootPath = root;
        this.email = email;
        this.rootRepository = this.rootPath + "/" + email + "/";
    }

    /**
     * Create: Data, Key and NotaryKey in repository with name email. 
     * Return: True if the creation was successful and false if not.
     * */
    public final boolean createRepository(final String email)
    {
        try 
        {
            Files.createDirectory(Paths.get(this.rootRepository));
            Files.createDirectory(Paths.get(this.rootRepository + "Repository"));
            Files.createDirectory(Paths.get(this.rootRepository + "SHADOW"));
            Files.createDirectory(Paths.get(this.rootRepository + "SHADOW/Key"));
            Files.createDirectory(Paths.get(this.rootRepository + "SHADOW/NotaryKey"));

            return true;
        } 
        catch (Exception e) 
        {
            return false;
        }
    }


    /**
     * Delete: Data, Key and NotaryKey in repository with name email.  
     * Return: True if the destruction was successful and false if not.
     * */
    public final boolean deleteRepository()
    {
        return FileSystemUtils.deleteRecursively(new File(this.rootRepository));
    }


    /** 
     * Creates directories.
     * */
    public boolean createDirectory(final String directory)
    {
        try 
        {
            Files.createDirectory(Paths.get(this.rootRepository + "Repository/" + directory));
            Files.createDirectory(Paths.get(this.rootRepository + "SHADOW/Key/" + directory));
            Files.createDirectory(Paths.get(this.rootRepository + "SHADOW/NotaryKey/" + directory));

            return true;
        } 
        catch (Exception e) 
        {
            return false;
        }
    }


    /** 
     * Delete directories.
     * */
    public boolean deleteDirectory(final String directory)
    {
        return FileSystemUtils.deleteRecursively(new File(this.rootRepository + directory));
    }


    /** 
     * Return: List of directories.
     * */
    public final List<String> listDirectories(final String stringPredicate,final String directory)
    {
        try
        {
            return Files.walk(Paths.get(this.rootRepository + directory)).filter((stringPredicate == "directory") ? Files::isDirectory : null)
                                                                            .filter(s -> {if(s.toString().contains(this.email + "SHADOW/KeyNotary")) return false; else return true;})
                                                                            .map(x -> x.toString().split(this.rootPath + "/")[1])
                                                                            .collect(Collectors.toList());
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return null;
        }
    }


    /** 
     * Return: List of files.
     * */
    public final List<String> listFiles(final String directory)
    {
        try
        {
            return Files.walk(Paths.get(this.rootRepository + directory)).filter(Files::isRegularFile)
                                                                            .map(p -> p.toString())
                                                                            .collect(Collectors.toList());
        } 
        catch (Exception e) 
        {
            return null;
        }   
    }


    /** 
     * Return: List of files and directories.
     * */
    public final List<String> listAllInDirectories(final String directory)
    {
        //TODO: Test if it works else use comment
        return Stream.of(new File(this.rootRepository + directory).list()).collect(Collectors.toList());

        /*
        try (Stream<Path> walk = Files.walk(Paths.get(rootPath + directory)))
        {
            List<String> files = walk.map(p -> p.toString())
                                        .collect(Collectors.toList());

            return files;
        } 
        catch (Exception e) 
        {
            return null;
        }  
        */ 
    }


    /** 
     * Return: Storage size
     * */
    public final long currentRepositorySize(final String directory)
    {
        try 
        {
            return Files.walk(Paths.get(this.rootRepository + directory))
                            .filter(p -> p.toFile().isFile())
                            .mapToLong(p -> p.toFile().length())
                            .sum();
        } 
        catch (IOException e) 
        {
            return 0;
        }
    }
}
