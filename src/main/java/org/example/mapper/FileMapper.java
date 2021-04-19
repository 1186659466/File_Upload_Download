package org.example.mapper;

import java.util.List;
import org.example.entity.Files;

public abstract interface FileMapper
{
  public abstract void insertFile(Files paramFiles);
  
  public abstract List<Files> selectFile();
  
  public abstract Files selectOneFile(String paramString);
}
