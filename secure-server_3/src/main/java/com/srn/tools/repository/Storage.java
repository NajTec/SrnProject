package com.srn.tools.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Vector;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.tomcat.util.http.fileupload.FileUtils;

public class Storage 
{
    private final String root;
    private String email;
    private String repositoryPath;

    public Storage(final String root)
    {
        this.root = root;
    }     

    public final boolean setMail(final String email)
    {
        boolean flag = false;

        if(email != null)
        {
            this.email = email;
            this.repositoryPath = this.root + "/" + this.email;
            flag = true;
        }

        return flag;
    }

    public final boolean createRepository()
    {
        boolean flag = false;
                
        final String directoryCipherText = this.repositoryPath + "/" + "Text";
        final String directoryCipherKey = this.repositoryPath + "/" + "Key";
        final String directoryCipherKeyNotary = this.repositoryPath + "/" + "KeyNotary";

        File cipherTextPath = new File(directoryCipherText);
        File cipherKeyPath = new File(directoryCipherKey);
        File cipherKeyNotaryPath = new File(directoryCipherKeyNotary);
        
        if(!cipherTextPath.exists() && !cipherKeyPath.exists() && !cipherKeyNotaryPath.exists())
        {
            cipherTextPath.mkdirs();
            cipherKeyPath.mkdirs();
            cipherKeyNotaryPath.mkdirs();
            flag = true;
        }    
        
        return flag;
    }

    public final boolean deleteRepository()
    {
        boolean flag = false;
     
        try 
        {
            final String directory = this.repositoryPath + "/";
            File directoryPath = new File(directory);

            if(directoryPath.exists())
            {
                FileUtils.cleanDirectory(directoryPath);
                directoryPath.delete();
                flag = true;
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        return flag;
    }

    public final boolean createDirectory(final String path)
    {
        boolean flag = false;

        final String directoryCipherText = this.repositoryPath + "/Text/" + path;
        final String directoryCipherKeyNotary = this.repositoryPath + "/KeyNotary/" + path;

        File cipherTextPath = new File(directoryCipherText);
        File cipherKeyNotaryPath = new File(directoryCipherKeyNotary);
        
        if(!cipherTextPath.exists() && !cipherKeyNotaryPath.exists())
        {
            cipherTextPath.mkdirs();
            cipherKeyNotaryPath.mkdirs();
            flag = true;
        } 

        return flag;
    }

    public final boolean deleteDirectory(final String path)
    {
        boolean flag = false;

        try 
        {
            final String directoryCipherText = this.repositoryPath + "/Text/" + path;
            final String directoryCipherKeyNotary = this.repositoryPath + "/KeyNotary/" + path;

            File directoryCipherTextPath = new File(directoryCipherText);
            File cipherKeyNotaryPath = new File(directoryCipherKeyNotary);

            if(directoryCipherTextPath.exists() && cipherKeyNotaryPath.exists())
            {
                FileUtils.cleanDirectory(directoryCipherTextPath);
                directoryCipherTextPath.delete();

                FileUtils.cleanDirectory(cipherKeyNotaryPath);
                cipherKeyNotaryPath.delete();

                flag = true;
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        return flag;
    }

    public final Vector<String> listAllFileDirectories(final String stringPredicate)
    {
        Vector<String> list = null;
        Predicate<? super Path> predicate = null;

        if(stringPredicate == "directory")
            predicate = Files::isDirectory;

        try (Stream<Path> walk = Files.walk(Paths.get(this.repositoryPath))) 
        {
            List<String> result = walk.filter(predicate)
                                        .map(x -> x.toString())
                                        .collect(Collectors.toList());

            Vector<String> vector = new Vector<String>();            
            for(int i = 0; i < result.size(); ++i)
                if(!result.get(i).contains(this.email + "/" + "KeyNotary"))
                    vector.add(result.get(i).split(root + "/")[1]);

            list = vector;
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        return list;
    }

    public final boolean storeFileInDirectory(final String dataDirectory, final Object object)
    {
        boolean flag = false;

        try 
        {
            File file = new File(this.repositoryPath + "/Text/" + dataDirectory);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(object.toString().getBytes());
            fos.close();

            flag = true;
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        return flag;
    }

    public final Object readFileFromDirectory(final String dataDirectory)
    {
        Object object = null;

        try 
        {
            File file = new File(this.repositoryPath + "/Text/" + dataDirectory);
            FileInputStream fis = new FileInputStream(file);

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            String data = "";
            while(fis.read(byteBuffer.array()) != -1)
                data += new String(byteBuffer.array(), StandardCharsets.UTF_8); // data += Charset.forName("ISO-8859-1").decode(byteBuffer); Both works but which on is better to the speed?
                
            
            object = data.split("\0")[0];

            fis.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        return object;
    }
       
    
}
