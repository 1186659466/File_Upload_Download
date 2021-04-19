package org.example.entity;

public class Files
{
  private String FId;
  private Long FSize;
  private String FStyle;
  private String FName;
  private String FTime;
  private String FAddress;
  
  public String getFId()
  {
    return this.FId;
  }
  
  public void setFId(String FId)
  {
    this.FId = FId;
  }
  
  public Long getFSize()
  {
    return this.FSize;
  }
  
  public void setFSize(Long FSize)
  {
    this.FSize = FSize;
  }
  
  public String getFStyle()
  {
    return this.FStyle;
  }
  
  public void setFStyle(String FStyle)
  {
    this.FStyle = FStyle;
  }
  
  public String getFName()
  {
    return this.FName;
  }
  
  public void setFName(String FName)
  {
    this.FName = FName;
  }
  
  public String getFTime()
  {
    return this.FTime;
  }
  
  public void setFTime(String FTime)
  {
    this.FTime = FTime;
  }
  
  public String getFAddress()
  {
    return this.FAddress;
  }
  
  public void setFAddress(String FAddress)
  {
    this.FAddress = FAddress;
  }
  
  public String toString()
  {
    return "Files{FId='" + this.FId + '\'' + ", FSize='" + this.FSize + '\'' + ", FStyle='" + this.FStyle + '\'' + ", FName='" + this.FName + '\'' + ", FTime='" + this.FTime + '\'' + ", FAddress='" + this.FAddress + '\'' + '}';
  }
}
