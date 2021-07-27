package com.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Vector;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        final String rootPath = this.rootPath + "/" + email;

        try 
        {
            Files.createDirectory(Paths.get(rootPath));
            Files.createDirectory(Paths.get(rootPath + "/" + "Storage"));
            Files.createDirectory(Paths.get(rootPath + "/" + "Key"));
            Files.createDirectory(Paths.get(rootPath + "/" + "NotaryKey"));

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
        final String rootPath = this.rootPath + "/" + email;
     
        try 
        {
            Files.walkFileTree(Paths.get(rootPath), 
                                    new SimpleFileVisitor<Path>() 
                                    {
                                        @Override
                                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException 
                                        {
                                            Files.delete(file);

                                            return FileVisitResult.CONTINUE;
                                        }
                                                        
                                        @Override
                                        public FileVisitResult postVisitDirectory(Path directory, IOException exc) throws IOException 
                                        {
                                            Files.delete(directory);

                                            return FileVisitResult.CONTINUE;
                                        }
                                    }
                                );

            return true;
          } 
          catch(IOException e)
          {
            return false;
          }
    }


    /** 
     * Creates directories.
     * */
    public boolean createDirectory(final String email, final String directory)
    {
        final String rootPath = this.rootPath + "/" + email;

        try 
        {
            Files.createDirectory(Paths.get(rootPath + "/" + "Storage" + "/" + directory));
            Files.createDirectory(Paths.get(rootPath + "/" + "Key" + "/" + directory));
            Files.createDirectory(Paths.get(rootPath + "/" + "NotaryKey" + "/" + directory));

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
        final String rootPath = this.rootPath + "/" + email + "/" + "Storage" + "/" + directory;
     
        try 
        {
            Files.walkFileTree(Paths.get(rootPath), 
                                    new SimpleFileVisitor<Path>() 
                                    {
                                        @Override
                                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException 
                                        {
                                            Files.delete(file);

                                            return FileVisitResult.CONTINUE;
                                        }
                                                        
                                        @Override
                                        public FileVisitResult postVisitDirectory(Path directory, IOException exc) throws IOException 
                                        {
                                            Files.delete(directory);

                                            return FileVisitResult.CONTINUE;
                                        }
                                    }
                                );

            return true;
          } 
          catch(IOException e)
          {
            return false;
          }
    }


    /** 
     * Return: List of directories.
     * */
    public final Vector<String> listDirectories(final String stringPredicate, final String email, final String directory)
    {
        Predicate<? super Path> predicate = null;

        if(stringPredicate == "directory")
            predicate = Files::isDirectory;

        try (Stream<Path> walk = Files.walk(Paths.get(this.rootPath + "/" + email + "/" + directory))) 
        {
            List<String> result = walk.filter(predicate)
                                        .map(x -> x.toString())
                                        .collect(Collectors.toList());

            Vector<String> vector = new Vector<String>();            
            for(String value : result)
                if(!value.contains(email + "/" + "KeyNotary"))
                    vector.add(value.split(this.rootPath + "/")[1]);

            return vector;
        } 
        catch (IOException e) 
        {
            return null;
        }
    }


    /** 
     * Return: List of files.
     * */
    public final Vector<String> listFiles(final String directory)
    {
        Vector<String> files = new Vector<String>();
        File[] listOfFiles = new File(directory).listFiles();

        for(File file : listOfFiles)
            if(file.isFile())
                files.add(file.getName());

        return files;    
    }


    /** 
     * Return: List of files and directories.
     * */
    public final Vector<String> listFilesAndDirectories(final String directory)
    {
        Vector<String> files = new Vector<String>();
        File[] listOfFiles = new File(directory).listFiles();

        for(File file : listOfFiles)
            if(file.isFile() || file.isDirectory())
                files.add(file.getName());

        return files;    
    }
    
}
