package com.storage;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Service 
{
    private final String repository;

    public Service(final String repository)
    {
        this.repository = repository;
    }
    

    /**
     * Delete: File in repository with name email. 
     * Return: True if the destruction was successful and false if not
     * */
    public final boolean deleteFile(final String fileDirectory)
    {
        try 
        {
            Files.delete(Paths.get(this.repository + fileDirectory));

            return true;
        } 
        catch (Exception e) 
        {
            return false;
        }
    }


    /**
     * copyFileToDirectory: File in repository with name email. 
     * Return: True if the copy was successful and false if not
     * */
    public final boolean copyFile(final String sourcePath, final String destinationPath)
    {
        try 
        {
            Files.copy(Paths.get(this.repository + sourcePath), Paths.get(this.repository + destinationPath));

            return true;
        } 
        catch (IOException e) 
        {
            return false;
        }
    }


    /**
     * moveFile: File in repository with name email. 
     * Return: True if the move was successful and false if not
     * */
    public final boolean moveFile(final String sourcePath, final String destinationPath)
    {
        try 
        {
            Files.move(Paths.get(this.repository + sourcePath), Paths.get(this.repository + destinationPath),
                        StandardCopyOption.REPLACE_EXISTING);

            return true;
        } 
        catch (IOException e) 
        {
            return false;
        }
    }


    //TODO: Server muss eine 10GB Nachricht erhalten!
    public final boolean readFile(final String sourcePath)
    {
        try(
            InputStream inputStream = new FileInputStream(this.repository + sourcePath);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        )
        {
            byte[] buffer = new byte[512 * 1024];
            int read;

            while((read = bufferedInputStream.read(buffer, 0, buffer.length)) != 1)
                //outputStream.write(buffer, 0, read);

            bufferedInputStream.close();
            inputStream.close();

            return true;
        } 
        catch (Exception e) 
        {
            return false;
        }

    }
}
