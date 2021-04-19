package org.example.service.impl;

import java.util.List;
import org.example.entity.Files;
import org.example.mapper.FileMapper;
import org.example.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService
  implements IFileService
{
  @Autowired
  private FileMapper fileMapper;
  
  public void insertFile(Files files)
  {
    this.fileMapper.insertFile(files);
  }
  
  public List<Files> selectFile()
  {
    return this.fileMapper.selectFile();
  }
  
  public Files selectOneFile(String FId)
  {
    return this.fileMapper.selectOneFile(FId);
  }
}
