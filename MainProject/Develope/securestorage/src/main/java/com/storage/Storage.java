package com.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.util.FileSystemUtils;

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
    

    /**
     * Create: Data, Key and NotaryKey in repository with name email. 
     * Return: True if the creation was successful and false if not.
     * */
    public final boolean createRepository(final String email)
    {
        final String rootPath = this.rootPath + "/" + email + "/";

        try 
        {
            Files.createDirectory(Paths.get(rootPath));
            Files.createDirectory(Paths.get(rootPath + "Repository"));
            Files.createDirectory(Paths.get(rootPath + "Key"));
            Files.createDirectory(Paths.get(rootPath + "NotaryKey"));

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
    public final boolean deleteRepository(final String email)
    {
        final String rootPath = this.rootPath + "/" + email + "/";
     
        return FileSystemUtils.deleteRecursively(new File(rootPath));
    }


    /** 
     * Creates directories.
     * */
    public boolean createDirectory(final String email, final String directory)
    {
        final String rootPath = this.rootPath + "/" + email + "/";

        try 
        {
            Files.createDirectory(Paths.get(rootPath + "Repository" + "/" + directory));
            Files.createDirectory(Paths.get(rootPath + "Key" + "/" + directory));
            Files.createDirectory(Paths.get(rootPath + "NotaryKey" + "/" + directory));

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
    public boolean deleteDirectory(final String email, final String directory)
    {
        final String rootPath = this.rootPath + "/" + email + "/" + "Repository" + "/";
     
        return FileSystemUtils.deleteRecursively(new File(rootPath + directory));
    }


    /** 
     * Return: List of directories.
     * */
    public final List<String> listDirectories(final String stringPredicate, final String email, final String directory)
    {
        Predicate<? super Path> predicate = null;

        if(stringPredicate == "directory")
            predicate = Files::isDirectory;

        final String rootPath = this.rootPath + "/" + email + "/";

        try (Stream<Path> walk = Files.walk(Paths.get(rootPath + directory+"/"))) 
        {
            List<String> result = walk.filter(predicate)
                                        .filter(s -> {if(s.toString().contains(email + "/" + "KeyNotary")) return false; else return true;})
                                        .map(x -> x.toString().split(this.rootPath + "/")[1])
                                        .collect(Collectors.toList());

            return result;
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
    public final List<String> listFiles(final String email, final String directory)
    {
        final String rootPath = this.rootPath + "/" + email + "/" + "Repository" + "/";

        try (Stream<Path> walk = Files.walk(Paths.get(rootPath + directory)))
        {
            List<String> files = walk.filter(Files::isRegularFile)
                                        .map(p -> p.toString())
                                        .collect(Collectors.toList());

            return files;
        } 
        catch (Exception e) 
        {
            return null;
        }   
    }


    /** 
     * Return: List of files and directories.
     * */
    public final List<String> listAllInDirectories(final String email, final String directory)
    {
        final String rootPath = this.rootPath + "/" + email + "/" + "Repository" + "/";

        //TODO: Test if it works else use comment
        return Stream.of(new File(rootPath + directory).list()).collect(Collectors.toList());

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
    public final long currentRepositorySize(final String email, final String directory)
    {
        long size = 0;

        final String mainDirectory = this.rootPath + "/" + email + "/" + "Repository" + "/";

        try 
        {
            size = Files.walk(Paths.get(mainDirectory + directory))
                            .filter(p -> p.toFile().isFile())
                            .mapToLong(p -> p.toFile().length())
                            .sum();

            return size;
        } 
        catch (IOException e) 
        {
            return 0;
        }
    }
}
