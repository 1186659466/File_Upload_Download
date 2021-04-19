package org.example.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.example.entity.Files;
import org.example.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController
{
  @Autowired
  private IFileService fileService;
  
  @GetMapping({"/upload.html"})
  public String indexView()
  {
    return "catalog";
  }
  
  @GetMapping({"/upload"})
  public String indexView1()
  {
    return "upload";
  }
  
  @GetMapping({"/download"})
  public String indexView2()
  {
    return "redirect:uploadFileHandler1";
  }
  
  @PostMapping({"/uploadFileHandler"})
  public String uploadFileHandler(MultipartFile uploadFile, HttpServletRequest request)
  {
    Files files = new Files();
    String path = request.getServletContext().getRealPath("/upload");
    File rootFile = new File(path);
    if (!rootFile.exists()) {
      rootFile.mkdirs();
    }
    try
    {
      InputStream fis = uploadFile.getInputStream();
      byte[] bs = new byte[fis.available()];
      fis.read(bs, 0, bs.length);
      fis.close();
      
      UUID uuid = UUID.randomUUID();
      String s = uuid.toString();
      files.setFId(s);
      
      files.setFSize(Long.valueOf(uploadFile.getSize()));
      
      String fileName = uploadFile.getOriginalFilename();
      String fileStyle = fileName.substring(fileName.lastIndexOf("."), fileName.length());
      files.setFStyle(fileStyle);
      
      String fileRealName = fileName.substring(0, fileName.lastIndexOf("."));
      files.setFName(fileRealName);
      
      SimpleDateFormat sdf = new SimpleDateFormat();
      sdf.applyPattern("yyyy-MM-dd");
      Date date = new Date();
      files.setFTime(sdf.format(date));
      
      files.setFAddress(path);
      
      this.fileService.insertFile(files);
      
      OutputStream fos = new FileOutputStream(path + "/" + s + fileStyle);
      fos.write(bs, 0, bs.length);
      fos.flush();
      fos.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return "redirect:uploadFileHandler1";
  }
  
  @GetMapping({"/uploadFileHandler1"})
  public String loadUploadFile(HttpServletRequest request, Model model)
  {
    String path = request.getServletContext().getRealPath("/upload");
    File rootFile = new File(path);
    if (rootFile.exists())
    {
      List<String> fileNames = Arrays.asList(rootFile.list());
      model.addAttribute("fileNames", fileNames);
    }
    return "download";
  }
  
  @PostMapping({"/downloadHandler"})
  public ResponseEntity<byte[]> downloadHandler(HttpServletRequest request, String fileName)
    throws Exception
  {
    String path = request.getServletContext().getRealPath("/upload");
    File file = new File(path + File.separator + fileName);
    
    HttpHeaders headers = new HttpHeaders();
    headers.setContentDispositionFormData("attachment", fileName);
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    return new ResponseEntity(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
  }
  
  @RequestMapping({"/loadFile"})
  @ResponseBody
  public List<Files> loadFile()
  {
    List<Files> files = this.fileService.selectFile();
    return files;
  }
  
  @PostMapping({"/loadOneFile"})
  @ResponseBody
  public Files loadOneFile(String fileName)
  {
    String s = fileName.substring(0, fileName.lastIndexOf("."));
    return this.fileService.selectOneFile(s);
  }
}
