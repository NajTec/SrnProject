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
    private final String rootPath;

    public Service(final String rootPath)
    {
        this.rootPath = rootPath;
    }
    

    /**
     * Delete: File in repository with name email. 
     * Return: True if the destruction was successful and false if not
     * */
    public final boolean deleteFile(final String email, final String fileDirectory)
    {
        final String rootPath = this.rootPath + "/" + email;

        try 
        {
            Files.delete(Paths.get(rootPath + "/" + "Storage" + "/" + fileDirectory));

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
    public final boolean copyFile(final String email, final String sourcePath, final String destinationPath)
    {
        final String rootPath = this.rootPath + "/" + email + "/";

        try 
        {
            Files.copy(Paths.get(rootPath + "/" + sourcePath), Paths.get(rootPath + "/" + destinationPath));

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
    public final boolean moveFile(final String email, final String sourcePath, final String destinationPath)
    {
        final String rootPath = this.rootPath + "/" + email;

        try 
        {
            Files.move(Paths.get(rootPath + "/" + sourcePath), Paths.get(rootPath + "/" + destinationPath),
                        StandardCopyOption.REPLACE_EXISTING);

            return true;
        } 
        catch (IOException e) 
        {
            return false;
        }
    }


    //Server muss eine 10GB Nachricht erhalten!
    public final boolean readFile(final String email, final String sourcePath)
    {
        final String rootPath = this.rootPath + "/" + email + "/";

        try(
            InputStream inputStream = new FileInputStream(rootPath + sourcePath);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        )
        {
            byte[] buffer = new byte[4 * 1024];
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
