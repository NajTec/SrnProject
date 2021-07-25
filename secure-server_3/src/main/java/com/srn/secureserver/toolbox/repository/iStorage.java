package com.srn.secureserver.toolbox.repository;

import java.util.Vector;

public interface iStorage 
{
    public String getStorage();
    public String getRepositoryPath();
    public void setStorage(final String root);
    public boolean setMail(final String email);
    public boolean createRepository();
    public boolean deleteRepository();
    public boolean createDirectory(final String path);
    public boolean deleteDirectory(final String path);
    public Vector<String> listAllFileDirectories(final String stringPredicate);
    public boolean storeFileToDirectory(final String mod, final String dataDirectory, final Object object);
    public Object readFileFromDirectory(final String mod, final String dataDirectory);
}

