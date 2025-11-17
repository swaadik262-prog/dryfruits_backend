package com.dryfruits.dryfruitsbackend.dto;

public class SaveUserJsonRequest {
    private String name;
    private String phoneNo;
    private String type;
    private String base64Image;
    // getters/setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhoneNo() { return phoneNo; }
    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getBase64Image() { return base64Image; }
    public void setBase64Image(String base64Image) { this.base64Image = base64Image; }
}