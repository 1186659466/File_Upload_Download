package org.example.service;

import java.util.List;
import org.example.entity.Files;

public abstract interface IFileService
{
  public abstract void insertFile(Files paramFiles);
  
  public abstract List<Files> selectFile();
  
  public abstract Files selectOneFile(String paramString);
}
